package com.example.alexbig.smartape.api.deserializer;

import com.example.alexbig.smartape.models.Quiz;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class QuizDeserializer implements JsonDeserializer<Quiz>{

    @Override
    public Quiz deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Quiz quiz = new Quiz();
        JsonObject jsonObject = json.getAsJsonObject();

        quiz.setTitle(jsonObject.get("Titulo").getAsString());
        quiz.setDescription(jsonObject.get("Descripcion").getAsString());
        quiz.setCreator(jsonObject.get("Creador").getAsString());
        quiz.setStatus(jsonObject.get("Estado").getAsInt());
        quiz.setTimeLimit(jsonObject.get("Tiempo_limite").getAsString());
        quiz.setCategory(jsonObject.get("Categoria").getAsString());
        quiz.setNumQuestions(jsonObject.get("Total_preguntas").getAsInt());

        return quiz;
    }
}
