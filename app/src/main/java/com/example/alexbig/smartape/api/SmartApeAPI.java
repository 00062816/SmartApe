package com.example.alexbig.smartape.api;

import com.example.alexbig.smartape.models.Question;
import com.example.alexbig.smartape.models.Quiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SmartApeAPI {
    String BASE_URL = "http://smartapes.herokuapp.com";

    @FormUrlEncoded
    @POST("/usuario/login")
    Call<String> login(@Field("Correo") String username, @Field("Password") String password);

    @GET("/quiz")
    Call<List<Quiz>> getQuizzes();

    @GET("/preguntas/{questionId}")
    Call<Question> getQuestion(@Path("questionId") String questionId);
}
