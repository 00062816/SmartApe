package com.example.alexbig.smartape.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Andres on 5/7/2018.
 */

public interface SmartApesApi {
    String ENDPOINT = "https://smartapes.herokuapp.com/";

    @FormUrlEncoded
    @POST("/usuario/login")
    Call<String> token(@Field("Correo") String username, @Field("Password") String password);

}

