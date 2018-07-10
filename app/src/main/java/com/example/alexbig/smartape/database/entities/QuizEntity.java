package com.example.alexbig.smartape.database.objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "quiz_table")
public class Quiz {
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
    private Integer Descripcion;
    @ColumnInfo(name = "tiempo_limite")
    private String Tiempo_limite;
    @ColumnInfo(name = "created_date")
    private String Created_date;
    @ColumnInfo(name = "time_date")
    private long Time_date;
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

    public String getCreador() {
        return Creador;
    }

    public String getCategoria() {
        return Categoria;
    }

    public String getTitulo() {
        return Titulo;
    }

    public Integer getDescripcion() {
        return Descripcion;
    }

    public String getTiempo_limite() {
        return Tiempo_limite;
    }

    public String getCreated_date() {
        return Created_date;
    }

    public long getTime_date() {
        return Time_date;
    }

    public long getResueltos() {
        return Resueltos;
    }

    public long getAprobados() {
        return Aprobados;
    }

    public long getReprobados() {
        return Reprobados;
    }

    public long getVistos() {
        return Vistos;
    }

    public long getGuardados() {
        return Guardados;
    }

    public long getFavoritos() {
        return Favoritos;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setCreador(String creador) {
        Creador = creador;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setDescripcion(Integer descripcion) {
        Descripcion = descripcion;
    }

    public void setTiempo_limite(String tiempo_limite) {
        Tiempo_limite = tiempo_limite;
    }

    public void setCreated_date(String created_date) {
        Created_date = created_date;
    }

    public void setTime_date(long time_date) {
        Time_date = time_date;
    }

    public void setResueltos(long resueltos) {
        Resueltos = resueltos;
    }

    public void setAprobados(long aprobados) {
        Aprobados = aprobados;
    }

    public void setReprobados(long reprobados) {
        Reprobados = reprobados;
    }

    public void setVistos(long vistos) {
        Vistos = vistos;
    }

    public void setGuardados(long guardados) {
        Guardados = guardados;
    }

    public void setFavoritos(long favoritos) {
        Favoritos = favoritos;
    }
}
