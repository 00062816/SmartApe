package bigdreams.example.alexbig.smartape.utils;

import bigdreams.example.alexbig.smartape.models.Quiz;
import com.google.gson.Gson;

import bigdreams.example.alexbig.smartape.models.Quiz;

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
