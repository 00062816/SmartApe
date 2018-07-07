package com.example.alexbig.smartape.api.deserializer;

import com.example.alexbig.smartape.models.Quiz;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuizDeserializer implements JsonDeserializer<Quiz>{

    @Override
    public Quiz deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Quiz quiz = new Quiz();
        JsonObject jsonObject = json.getAsJsonObject();
        System.out.println("JSON "+jsonObject.toString());

        String title = jsonObject.get("Titulo").getAsString();
        if (title != null){
            quiz.setTitle(title);
        }else{
            quiz.setTitle("");
        }

        String description = jsonObject.get("Descripcion").getAsString();
        if (description != null){
            quiz.setDescription(description);
        }else{
            quiz.setDescription("");
        }

        String creator = jsonObject.get("Creador").getAsString();
        if (creator != null){
            quiz.setCreator(creator);
        }else{
            quiz.setCreator("");
        }

        int status = jsonObject.get("Estado").getAsInt();
        quiz.setStatus(status);

        int numQuestions = jsonObject.get("Total_preguntas").getAsInt();
        quiz.setNumQuestions(numQuestions);

        String timeLimit = jsonObject.get("Tiempo_limite").getAsString();
        if (timeLimit != null){
            quiz.setTimeLimit(timeLimit);
        }else{
            quiz.setTimeLimit("");
        }

        String category = jsonObject.get("Categoria").getAsString();
        if (category != null){
            quiz.setCategory(category);
        }else{
            quiz.setCategory("");
        }

        List<String> questions = new ArrayList<>();
        JsonArray array = jsonObject.getAsJsonArray("Preguntas");
        for (int i=0; i<array.size(); i++){
            questions.add(array.get(i).getAsString());
        }
        quiz.setQuestions(questions);

        return quiz;
    }
}
