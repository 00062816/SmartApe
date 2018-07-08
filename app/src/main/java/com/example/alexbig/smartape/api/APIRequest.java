package com.example.alexbig.smartape.api;

import com.example.alexbig.smartape.api.deserializers.QuestionDeserializer;
import com.example.alexbig.smartape.api.deserializers.QuizDeserializer;
import com.example.alexbig.smartape.api.deserializers.TokenDeserializer;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.models.Question;
import com.example.alexbig.smartape.models.Quiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
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

    private static String token = "";
    private SmartApeAPI smartApeAPI;
    private QuizViewModel quizViewModel;

    public APIRequest(QuizViewModel quizViewModel){
        this.quizViewModel = quizViewModel;
    }

    private void createAPIClient(Gson gson){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("x-access-token", ""+token).build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(SmartApeAPI.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        smartApeAPI = retrofit.create(SmartApeAPI.class);
    }

    public void login(String username, String password){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create();
        createAPIClient(gson);
        Call<String> login = smartApeAPI.login(username, password);
        login.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                token = response.body();
                downloadQuizzes();
                System.out.println("LOGIN "+token);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void downloadQuizzes(){
        Gson gson = new GsonBuilder().registerTypeAdapter(Quiz.class, new QuizDeserializer()).create();
        createAPIClient(gson);
        Call<List<Quiz>> getQuizzes = smartApeAPI.getQuizzes();
        getQuizzes.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, retrofit2.Response<List<Quiz>> response) {
                List<Quiz> quizList = response.body();
                if (quizList != null) {
                    quizViewModel.insertQuizzes(quizList);
                }
                System.out.println("QUIZZES DOWNLOADED "+quizList);
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void downloadQuestions(List<String> questionIds){
        if (questionIds != null) {
            for (String id : questionIds) {
                downloadQuestion(id);
            }
        }
    }

    public void downloadQuestion(String questionId){
        Gson gson = new GsonBuilder().registerTypeAdapter(Question.class, new QuestionDeserializer()).create();
        createAPIClient(gson);
        Call<Question> getQuestion = smartApeAPI.getQuestion(questionId);
        getQuestion.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, retrofit2.Response<Question> response) {
                System.out.println("QUESTION "+response.body());
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void uploadQuiz(Quiz quiz){
        createAPIClient(new Gson());
        String[] questionArray = new String[quiz.getQuestions().size()];
        questionArray = quiz.getQuestions().toArray(questionArray);
        Call<Void> uploadQuiz = smartApeAPI.uploadQuiz(quiz.getCategory(), quiz.getTitle(), quiz.getCreator(), quiz.getStatus(), quiz.getDescription(), quiz.getTimeLimit(), quiz.getNumQuestions(), questionArray, "");
        uploadQuiz.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                quizViewModel.insertQuiz(quiz);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void refresh(){
       login("uca@edu.sv","chaleco234");
    }
}
