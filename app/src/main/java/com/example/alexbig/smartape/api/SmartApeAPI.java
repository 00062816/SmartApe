package com.example.alexbig.smartape.api;

import com.example.alexbig.smartape.models.Quiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SmartApeAPI {
    String BASE_URL = "http://smartapes.herokuapp.com";

    @GET("/quiz")
    Call<List<Quiz>> getQuizzes();
}
