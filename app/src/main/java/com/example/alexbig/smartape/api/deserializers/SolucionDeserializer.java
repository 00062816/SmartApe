package com.example.alexbig.smartape.api.deserializers;

import com.example.alexbig.smartape.database.entities.Solucion;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class SolucionDeserializer implements JsonDeserializer<Solucion>{
    @Override
    public Solucion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Solucion soluciones = new Solucion();
        JsonObject jsonObject = json.getAsJsonObject();

        System.out.println("Solucion JSON "+jsonObject.toString());
        soluciones.setId(jsonObject.get("_id").getAsString());
        soluciones.setCorrecto(jsonObject.get("Correcto").getAsString());
        soluciones.setRespuesta_pregunta(jsonObject.get("respuesta_pregunta").getAsString());

        return soluciones;
    }
}
