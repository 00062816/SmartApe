package com.example.alexbig.smartape.utils;

import com.example.alexbig.smartape.models.Quiz;
import com.google.gson.Gson;
public class JsonConverter {

    public static String toJson(Quiz quiz){
        String json;
        Gson gson = new Gson();
        json = gson.toJson(quiz);
        System.out.println("JSON "+json);
        return json;
    }

    public static Quiz toQuiz(String json){
        Quiz quiz;
        Gson gson = new Gson();
        quiz = gson.fromJson(json, Quiz.class);
        return quiz;
    }
}
