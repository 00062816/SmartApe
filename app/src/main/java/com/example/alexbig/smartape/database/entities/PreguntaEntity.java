package com.example.alexbig.smartape.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "pregunta_table")
public class PreguntaEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;
    @ColumnInfo(name = "premisa")
    private String Premisa;
    @ColumnInfo(name = "tipo_pregunta")
    private int Tipo_pregunta;
    @ForeignKey(entity = QuizEntity.class, parentColumns = "_id", childColumns = "quizid",
            onDelete = CASCADE)
    @ColumnInfo(name = "quizid")
    private String quizid;
    @ColumnInfo(name = "fecha_creacion")
    private String fecha_creacion;

    @NonNull
    public String getId() {
        return id;
    }

    public String getPremisa() {
        return Premisa;
    }

    public int getTipo_pregunta() {
        return Tipo_pregunta;
    }

    public String getQuizid() {
        return quizid;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setPremisa(String premisa) {
        Premisa = premisa;
    }

    public void setTipo_pregunta(int tipo_pregunta) {
        Tipo_pregunta = tipo_pregunta;
    }

    public void setQuizid(String quizid) {
        this.quizid = quizid;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
