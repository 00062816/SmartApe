package com.example.alexbig.smartape.database.objects;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "usuario_table")
public class Usuario {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;
    @ColumnInfo(name = "correo")
    private String Correo;
    @ColumnInfo(name = "password")
    private String Password;
    @ColumnInfo(name = "descripcion")
    private String Descripcion;
    @ColumnInfo(name = "lugar")
    private String Lugar;
    @ColumnInfo(name = "nombre")
    private String Nombre;
    @ColumnInfo(name = "apellido")
    private String Apellido;
    @ColumnInfo(name = "display_name")
    private String DisplayName;
    @ColumnInfo(name = "collecion_favoritos")
    private String Collecion_favoritos;
    @ColumnInfo(name = "collecion_guardados")
    private String Collecion_guardados;
    @ColumnInfo(name = "collecion_quizzes")
    private String Collecion_quizzes;

    @NonNull
    public String getId() {
        return id;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getPassword() {
        return Password;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getLugar() {
        return Lugar;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public String getCollecion_favoritos() {
        return Collecion_favoritos;
    }

    public String getCollecion_guardados() {
        return Collecion_guardados;
    }

    public String getCollecion_quizzes() {
        return Collecion_quizzes;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public void setCollecion_favoritos(String collecion_favoritos) {
        Collecion_favoritos = collecion_favoritos;
    }

    public void setCollecion_guardados(String collecion_guardados) {
        Collecion_guardados = collecion_guardados;
    }

    public void setCollecion_quizzes(String collecion_quizzes) {
        Collecion_quizzes = collecion_quizzes;
    }
}
