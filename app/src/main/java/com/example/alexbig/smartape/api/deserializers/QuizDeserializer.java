package com.example.alexbig.smartape.api.deserializers;

import com.example.alexbig.smartape.models.Quiz;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuizDeserializer implements JsonDeserializer<Quiz>{

    @Override
    public Quiz deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Quiz quiz = new Quiz();
        List<String> questionList = new ArrayList<>();

        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.get("_id") != null){
            quiz.setId(jsonObject.get("_id").getAsString());
        }else {
            quiz.setId("");
        }

        if (jsonObject.get("Titulo") != null){
            quiz.setTitle(jsonObject.get("Titulo").getAsString());
        }else {
            quiz.setTitle("");
        }

        if (jsonObject.get("Categoria") != null){
            quiz.setCategory(jsonObject.get("Categoria").getAsString());
        }else {
            quiz.setCategory("");
        }

        if (jsonObject.get("Descripcion") != null){
            quiz.setDescription(jsonObject.get("Descripcion").getAsString());
        }else {
            quiz.setDescription("");
        }

        if (jsonObject.get("Estado") != null){
            quiz.setStatus(jsonObject.get("Estado").getAsInt());
        }else {
            quiz.setStatus(0);
        }

        if (jsonObject.get("Creador") != null){
            quiz.setCreator(jsonObject.get("Creador").getAsString());
        }else {
            quiz.setCreator("");
        }

        if (jsonObject.get("Tiempo_limite") != null){
            quiz.setTimeLimit(jsonObject.get("Tiempo_limite").getAsString());
        }else {
            quiz.setTimeLimit("");
        }

        if (jsonObject.get("Total_preguntas") != null){
            quiz.setNumQuestions(jsonObject.get("Total_preguntas").getAsInt());
        }else {
            quiz.setNumQuestions(0);
        }

        if (jsonObject.get("Fecha_creacion") != null){
            quiz.setCreated_date(jsonObject.get("Fecha_creacion").getAsString());
        }else {
            quiz.setCreated_date("");
        }

        JsonArray questionsJsonArray = jsonObject.get("Preguntas").getAsJsonArray();

        for (JsonElement jsonElement : questionsJsonArray){

            questionList.add(jsonElement.getAsString());

        }

        quiz.setQuestionsIds(questionList);

        JsonObject socialElement = jsonObject.get("Elementos_sociales").getAsJsonObject();
        quiz.setResueltos(socialElement.get("Resueltos").getAsLong());
        quiz.setAprobados(socialElement.get("Aprobados").getAsLong());
        quiz.setReprobados(socialElement.get("Reprobados").getAsLong());
        quiz.setVistos(socialElement.get("Vistos").getAsLong());
        quiz.setGuardados(socialElement.get("Guardados").getAsLong());
        quiz.setFavoritos(socialElement.get("Favoritos").getAsLong());

        return quiz;
    }
}
