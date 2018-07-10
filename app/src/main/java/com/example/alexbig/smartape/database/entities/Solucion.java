package com.example.alexbig.smartape.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "solucion_table")
public class Solucion {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;
    @ForeignKey(entity = PreguntaEntity.class, parentColumns = "_id", childColumns = "preguntaid",
            onDelete = CASCADE)
    @ColumnInfo(name = "preguntaid")
    private String preguntaid;
    @ColumnInfo(name = "correcto")
    private String Correcto;
    @ColumnInfo(name = "respuesta_pregunta")
    private String Respuesta_pregunta;


    @NonNull
    public String getId() {
        return id;
    }

    public String getPreguntaid() {
        return preguntaid;
    }

    public String getCorrecto() {
        return Correcto;
    }

    public String getRespuesta_pregunta() {
        return Respuesta_pregunta;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setPreguntaid(String preguntaid) {
        this.preguntaid = preguntaid;
    }

    public void setCorrecto(String correcto) {
        Correcto = correcto;
    }

    public void setRespuesta_pregunta(String respuesta_pregunta) {
        Respuesta_pregunta = respuesta_pregunta;
    }
}
