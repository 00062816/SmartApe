package com.example.alexbig.smartape.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "quiz_table")
public class QuizEntity {


    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;
    @ForeignKey(entity = Usuario.class, parentColumns = "_id", childColumns = "creador",
            onDelete = CASCADE)
    @ColumnInfo(name = "creador")
    private String Creador;
    @ColumnInfo(name = "categoria")
    private String Categoria;
    @ColumnInfo(name = "titulo")
    private String Titulo;
    @ColumnInfo(name = "descripcion")
    private String Descripcion;
    @ColumnInfo(name = "tiempo_limite")
    private String Tiempo_limite;
    @ColumnInfo(name = "created_date")
    private String Created_date;
    @ColumnInfo(name = "status")
    private int Estado;
    @ColumnInfo(name = "Total_preguntas")
    private int Total_questions;
    @ColumnInfo(name = "resueltos")
    private long Resueltos;
    @ColumnInfo(name = "aprobados")
    private long Aprobados;
    @ColumnInfo(name = "reprobados")
    private long Reprobados;
    @ColumnInfo(name = "vistos")
    private long Vistos;
    @ColumnInfo(name = "guardados")
    private long Guardados;
    @ColumnInfo(name = "favoritos")
    private long Favoritos;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getCreador() {
        return Creador;
    }

    public void setCreador(String creador) {
        Creador = creador;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTiempo_limite() {
        return Tiempo_limite;
    }

    public void setTiempo_limite(String tiempo_limite) {
        Tiempo_limite = tiempo_limite;
    }

    public String getCreated_date() {
        return Created_date;
    }

    public void setCreated_date(String created_date) {
        Created_date = created_date;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public int getTotal_questions() {
        return Total_questions;
    }

    public void setTotal_questions(int total_questions) {
        Total_questions = total_questions;
    }

    public long getResueltos() {
        return Resueltos;
    }

    public void setResueltos(long resueltos) {
        Resueltos = resueltos;
    }

    public long getAprobados() {
        return Aprobados;
    }

    public void setAprobados(long aprobados) {
        Aprobados = aprobados;
    }

    public long getReprobados() {
        return Reprobados;
    }

    public void setReprobados(long reprobados) {
        Reprobados = reprobados;
    }

    public long getVistos() {
        return Vistos;
    }

    public void setVistos(long vistos) {
        Vistos = vistos;
    }

    public long getGuardados() {
        return Guardados;
    }

    public void setGuardados(long guardados) {
        Guardados = guardados;
    }

    public long getFavoritos() {
        return Favoritos;
    }

    public void setFavoritos(long favoritos) {
        Favoritos = favoritos;
    }
}
