package com.example.alexbig.smartape.api.deserializer;

import com.example.alexbig.smartape.models.Question;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class QuestionDeserializer implements JsonDeserializer<Question>{
    @Override
    public Question deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Question question = new Question();
        System.out.println("JSON "+json.getAsJsonObject().toString());
        /*JsonObject jsonObject = json.getAsJsonObject();

        System.out.println("JSON "+json.toString());
        question.setPremise(jsonObject.get("Premisa").getAsString());
        question.setType(jsonObject.get("Tipo_pregunta").getAsInt());*/

        return question;
    }
}
