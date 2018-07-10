package com.example.alexbig.smartape.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.activities.LoginActivity;
import com.example.alexbig.smartape.activities.MainActivity;
import com.example.alexbig.smartape.api.deserializers.QuestionDeserializer;
import com.example.alexbig.smartape.api.deserializers.QuizDeserializer;
import com.example.alexbig.smartape.api.deserializers.TokenDeserializer;
import com.example.alexbig.smartape.database.entities.PreguntaEntity;
import com.example.alexbig.smartape.database.entities.QuizEntity;
import com.example.alexbig.smartape.database.viewmodels.PreguntaViewModel;
import com.example.alexbig.smartape.database.viewmodels.QuestionViewModel;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.models.Question;
import com.example.alexbig.smartape.models.Quiz;
import com.example.alexbig.smartape.utils.ActivityManager;
import com.example.alexbig.smartape.utils.Toaster;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRequest {
    private static String token;
    private SmartApeAPI smartApeAPI;
    private Context context;

    public APIRequest(Context context) {
        this.context = context;
    }

    private void createAPIClient(Gson gson) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("x-access-token", "" + token).build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(SmartApeAPI.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        smartApeAPI = retrofit.create(SmartApeAPI.class);
    }

    public boolean checkLogin() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("logged", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("token")) {
            token = sharedPreferences.getString("token", null);
            //downloadQuizzes();
            return true;
        } else {
            return false;
        }
    }

    public void login(String username, String password, ProgressBar progressBar, RelativeLayout relativeLayout) {
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create();
        createAPIClient(gson);
        Call<String> login = smartApeAPI.login(username, password);
        login.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                System.out.println(response.code());
                if (response.code() == 200) {
                    Toaster.makeToast(context, "Login successful");

                    token = response.body();
                    SharedPreferences preferences = context.getSharedPreferences("logged", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", token);
                    editor.commit();

                    //downloadQuizzes();

                    ActivityManager.openMainActivity(context);
                    ActivityManager.closeActivity(context);
                } else if (response.code() == 404) {
                    Toaster.makeToast(context, "No user found");
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                } else if (response.code() == 500) {
                    Toaster.makeToast(context, "There was a problem finding the user");
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    Toaster.makeToast(context, "Error: check later");
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toaster.makeToast(context, "Time out");
                }
            }
        });
    }

    public void signIn(String email, String password, ProgressBar progressBar, RelativeLayout relativeLayout) {
        createAPIClient(new Gson());
        Call<Void> signIn = smartApeAPI.signIn(email, password);
        signIn.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                System.out.println(response.code());

                if (response.code() == 200) {
                    login(email, password, progressBar, relativeLayout);
                } else if (response.code() == 404) {
                    Toaster.makeToast(context, "No user found");
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                } else if (response.code() == 500) {
                    Toaster.makeToast(context, "There was a problem finding the user");
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    Toaster.makeToast(context, "Error: check later");
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toaster.makeToast(context, "Time out");
                }
            }
        });
    }

    public void downloadQuizzes(QuizViewModel quizViewModel) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Quiz.class, new QuizDeserializer()).create();
        createAPIClient(gson);
        Call<List<Quiz>> getQuizzes = smartApeAPI.getQuizzes();
        getQuizzes.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, retrofit2.Response<List<Quiz>> response) {
                List<Quiz> quizList = response.body();
                List<QuizEntity> insertlist = new ArrayList<>();
                if (quizList != null) {
                    for (Quiz quiz : quizList) {
                        QuizEntity quizEntity = new QuizEntity();

                        quizEntity.setId(quiz.getId());
                        quizEntity.setCreador(quiz.getCreator());
                        quizEntity.setCategoria(quiz.getCategory());
                        quizEntity.setTitulo(quiz.getTitle());
                        quizEntity.setDescripcion(quiz.getDescription());
                        quizEntity.setTiempo_limite(quiz.getTimeLimit());
                        quizEntity.setCreated_date(quiz.getCreated_date());
                        quizEntity.setEstado(quiz.getStatus());
                        quizEntity.setTotal_questions(quiz.getNumQuestions());
                        quizEntity.setResueltos(quiz.getResueltos());
                        quizEntity.setAprobados(quiz.getAprobados());
                        quizEntity.setReprobados(quiz.getReprobados());
                        quizEntity.setVistos(quiz.getVistos());
                        quizEntity.setGuardados(quiz.getGuardados());
                        quizEntity.setFavoritos(quiz.getFavoritos());

                        insertlist.add(quizEntity);
                    }
                    if (insertlist!=null)
                        quizViewModel.insertList(insertlist);
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void downloadSavedQuizzes(QuizViewModel quizViewModel) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Quiz.class, new QuizDeserializer()).create();
        createAPIClient(gson);
        Call<List<Quiz>> getSavedQuizzes = smartApeAPI.getSavedQuizzes();
        getSavedQuizzes.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, retrofit2.Response<List<Quiz>> response) {
                List<Quiz> quizList = response.body();
                List<QuizEntity> insertlist = new ArrayList<>();
                if (quizList != null) {
                    for (Quiz quiz : quizList) {
                        QuizEntity quizEntity = new QuizEntity();

                        quizEntity.setId(quiz.getId());
                        quizEntity.setCreador(quiz.getCreator());
                        quizEntity.setCategoria(quiz.getCategory());
                        quizEntity.setTitulo(quiz.getTitle());
                        quizEntity.setDescripcion(quiz.getDescription());
                        quizEntity.setTiempo_limite(quiz.getTimeLimit());
                        quizEntity.setCreated_date(quiz.getCreated_date());
                        quizEntity.setEstado(quiz.getStatus());
                        quizEntity.setTotal_questions(quiz.getNumQuestions());
                        quizEntity.setResueltos(quiz.getResueltos());
                        quizEntity.setAprobados(quiz.getAprobados());
                        quizEntity.setReprobados(quiz.getReprobados());
                        quizEntity.setVistos(quiz.getVistos());
                        quizEntity.setGuardados(quiz.getGuardados());
                        quizEntity.setFavoritos(quiz.getFavoritos());

                        insertlist.add(quizEntity);
                    }
                    if (insertlist!=null)
                        quizViewModel.insertList(insertlist);
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {

            }
        });
    }

    public void downloadFavedQuizzes(QuizViewModel quizViewModel) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Quiz.class, new QuizDeserializer()).create();
        createAPIClient(gson);
        Call<List<Quiz>> getSavedQuizzes = smartApeAPI.getFavedQuizzes();
        getSavedQuizzes.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, retrofit2.Response<List<Quiz>> response) {
                List<Quiz> quizList = response.body();
                List<QuizEntity> insertlist = new ArrayList<>();
                if (quizList != null) {
                    for (Quiz quiz : quizList) {
                        QuizEntity quizEntity = new QuizEntity();

                        quizEntity.setId(quiz.getId());
                        quizEntity.setCreador(quiz.getCreator());
                        quizEntity.setCategoria(quiz.getCategory());
                        quizEntity.setTitulo(quiz.getTitle());
                        quizEntity.setDescripcion(quiz.getDescription());
                        quizEntity.setTiempo_limite(quiz.getTimeLimit());
                        quizEntity.setCreated_date(quiz.getCreated_date());
                        quizEntity.setEstado(quiz.getStatus());
                        quizEntity.setTotal_questions(quiz.getNumQuestions());
                        quizEntity.setResueltos(quiz.getResueltos());
                        quizEntity.setAprobados(quiz.getAprobados());
                        quizEntity.setReprobados(quiz.getReprobados());
                        quizEntity.setVistos(quiz.getVistos());
                        quizEntity.setGuardados(quiz.getGuardados());
                        quizEntity.setFavoritos(quiz.getFavoritos());

                        insertlist.add(quizEntity);
                    }
                    if (insertlist!=null)
                        quizViewModel.insertList(insertlist);
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {

            }
        });
    }

    public void downloadCreatedQuizzes(QuizViewModel quizViewModel) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Quiz.class, new QuizDeserializer()).create();
        createAPIClient(gson);
        Call<List<Quiz>> getSavedQuizzes = smartApeAPI.getCreatedQuizzes();
        getSavedQuizzes.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, retrofit2.Response<List<Quiz>> response) {
                List<Quiz> quizList = response.body();
                List<QuizEntity> insertlist = new ArrayList<>();
                if (quizList != null) {
                    for (Quiz quiz : quizList) {
                        QuizEntity quizEntity = new QuizEntity();

                        quizEntity.setId(quiz.getId());
                        quizEntity.setCreador(quiz.getCreator());
                        quizEntity.setCategoria(quiz.getCategory());
                        quizEntity.setTitulo(quiz.getTitle());
                        quizEntity.setDescripcion(quiz.getDescription());
                        quizEntity.setTiempo_limite(quiz.getTimeLimit());
                        quizEntity.setCreated_date(quiz.getCreated_date());
                        quizEntity.setEstado(quiz.getStatus());
                        quizEntity.setTotal_questions(quiz.getNumQuestions());
                        quizEntity.setResueltos(quiz.getResueltos());
                        quizEntity.setAprobados(quiz.getAprobados());
                        quizEntity.setReprobados(quiz.getReprobados());
                        quizEntity.setVistos(quiz.getVistos());
                        quizEntity.setGuardados(quiz.getGuardados());
                        quizEntity.setFavoritos(quiz.getFavoritos());

                        insertlist.add(quizEntity);
                    }
                    if (insertlist!=null)
                        quizViewModel.insertList(insertlist);
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {

            }
        });
    }



    public void downloadQuestion(PreguntaViewModel preguntaViewModel, String questionId) {
        Gson gson = new GsonBuilder().registerTypeAdapter(PreguntaEntity.class, new QuestionDeserializer()).create();
        createAPIClient(gson);
        Call<List<PreguntaEntity>> getQuestion = smartApeAPI.getPreguntas(questionId);
        getQuestion.enqueue(new Callback<List<PreguntaEntity>>() {
            @Override
            public void onResponse(Call<List<PreguntaEntity>> call, retrofit2.Response<List<PreguntaEntity>> response) {
                List<PreguntaEntity> preguntaEntityListList = response.body();
                preguntaViewModel.insertList(preguntaEntityListList);

            }

            @Override
            public void onFailure(Call<List<PreguntaEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void downloadPreguntas(PreguntaViewModel preguntaViewModel, String Idquiz) {
        Gson gson = new GsonBuilder().registerTypeAdapter(PreguntaEntity.class, new QuestionDeserializer()).create();
        createAPIClient(gson);
        Call<List<PreguntaEntity>> getinfo = smartApeAPI.getPreguntas(Idquiz);
        getinfo.enqueue(new Callback<List<PreguntaEntity>>() {
            @Override
            public void onResponse(Call<List<PreguntaEntity>> call, retrofit2.Response<List<PreguntaEntity>> response) {
                List<PreguntaEntity> preguntaEntityListList = response.body();
            }

            @Override
            public void onFailure(Call<List<PreguntaEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void uploadQuiz(Quiz quiz) {
        createAPIClient(new Gson());
        Call<Void> uploadQuiz = smartApeAPI.uploadQuiz(quiz.getCategory(), quiz.getTitle(), quiz.getStatus(), quiz.getDescription(), quiz.getTimeLimit());
        uploadQuiz.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void refresh() {

    }
}
