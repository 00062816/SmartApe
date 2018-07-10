package com.example.alexbig.smartape.api.deserializers;

import com.example.alexbig.smartape.database.objects.PreguntaEntity;
import com.example.alexbig.smartape.database.objects.SolucionEntity;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class SolucionDeserializer implements JsonDeserializer<SolucionEntity>{
    @Override
    public SolucionEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        SolucionEntity soluciones = new SolucionEntity();
        JsonObject jsonObject = json.getAsJsonObject();

        System.out.println("Solucion JSON "+jsonObject.toString());
        soluciones.setId(jsonObject.get("_id").getAsString());
        soluciones.setCorrecto(jsonObject.get("Correcto").getAsString());
        soluciones.setRespuesta_pregunta(jsonObject.get("respuesta_pregunta").getAsString());

        return soluciones;
    }
}
