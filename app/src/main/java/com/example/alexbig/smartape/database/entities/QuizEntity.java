package com.example.alexbig.smartape.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Andres on 4/7/2018.
 */

@Entity
public class QuizEntity {
    @NonNull
    @PrimaryKey
    private String entQuizId;
    private String entQuizCategoria, entQuizTitulo, entQuizCreador;
    private int entQuizEstado;
    private String entQuizDescripcion, entQuizTiempoLimite;
    private int entQuizTotalPreguntas;
    private int entQuizFechaCreacion;

    public QuizEntity(@NonNull String entQuizId, String entQuizCategoria, String entQuizTitulo, String entQuizCreador, int entQuizEstado, String entQuizDescripcion, String entQuizTiempoLimite, int entQuizTotalPreguntas, int entQuizFechaCreacion) {
        this.entQuizId = entQuizId;
        this.entQuizCategoria = entQuizCategoria;
        this.entQuizTitulo = entQuizTitulo;
        this.entQuizCreador = entQuizCreador;
        this.entQuizEstado = entQuizEstado;
        this.entQuizDescripcion = entQuizDescripcion;
        this.entQuizTiempoLimite = entQuizTiempoLimite;
        this.entQuizTotalPreguntas = entQuizTotalPreguntas;
        this.entQuizFechaCreacion = entQuizFechaCreacion;
    }

    @NonNull
    public String getEntQuizId() {
        return entQuizId;
    }

    public void setEntQuizId(@NonNull String entQuizId) {
        this.entQuizId = entQuizId;
    }

    public String getEntQuizCategoria() {
        return entQuizCategoria;
    }

    public void setEntQuizCategoria(String entQuizCategoria) {
        this.entQuizCategoria = entQuizCategoria;
    }

    public String getEntQuizTitulo() {
        return entQuizTitulo;
    }

    public void setEntQuizTitulo(String entQuizTitulo) {
        this.entQuizTitulo = entQuizTitulo;
    }

    public String getEntQuizCreador() {
        return entQuizCreador;
    }

    public void setEntQuizCreador(String entQuizCreador) {
        this.entQuizCreador = entQuizCreador;
    }

    public int getEntQuizEstado() {
        return entQuizEstado;
    }

    public void setEntQuizEstado(int entQuizEstado) {
        this.entQuizEstado = entQuizEstado;
    }

    public String getEntQuizDescripcion() {
        return entQuizDescripcion;
    }

    public void setEntQuizDescripcion(String entQuizDescripcion) {
        this.entQuizDescripcion = entQuizDescripcion;
    }

    public String getEntQuizTiempoLimite() {
        return entQuizTiempoLimite;
    }

    public void setEntQuizTiempoLimite(String entQuizTiempoLimite) {
        this.entQuizTiempoLimite = entQuizTiempoLimite;
    }

    public int getEntQuizTotalPreguntas() {
        return entQuizTotalPreguntas;
    }

    public void setEntQuizTotalPreguntas(int entQuizTotalPreguntas) {
        this.entQuizTotalPreguntas = entQuizTotalPreguntas;
    }

    public int getEntQuizFechaCreacion() {
        return entQuizFechaCreacion;
    }

    public void setEntQuizFechaCreacion(int entQuizFechaCreacion) {
        this.entQuizFechaCreacion = entQuizFechaCreacion;
    }
}
