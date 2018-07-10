package com.example.alexbig.smartape.api.deserializers;

import com.example.alexbig.smartape.database.objects.PreguntaEntity;
import com.example.alexbig.smartape.models.Question;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class QuestionDeserializer implements JsonDeserializer<PreguntaEntity>{
    @Override
    public PreguntaEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        PreguntaEntity question = new PreguntaEntity();
        JsonObject jsonObject = json.getAsJsonObject();

        System.out.println("QUESTION JSON "+jsonObject.toString());
        question.setId(jsonObject.get("_id").getAsString());
        question.setPremisa(jsonObject.get("Premisa").getAsString());
        question.setTipo_pregunta(jsonObject.get("Tipo_pregunta").getAsInt());
        question.setFecha_creacion(jsonObject.get("Fecha_creacion").getAsString());
        question.setQuizid(jsonObject.get("Id_quiz").getAsString());
        return question;
    }
}
