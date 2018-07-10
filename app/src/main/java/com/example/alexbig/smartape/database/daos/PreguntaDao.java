package com.example.alexbig.smartape.database.daos;

/**
 * Created by Andres on 5/7/2018.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.alexbig.smartape.database.entities.Pregunta;

import java.util.List;

@Dao
public interface PreguntaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pregunta... preguntas);

    @Delete
    void delete(Pregunta pregunta);

    @Update
    void update(Pregunta... preguntas);

    @Query("DELETE FROM pregunta_table")
    void DeleteAllPreguntas();

    @Query("SELECT * FROM pregunta_table WHERE _id IN (:preguntaIds)")
    List<Pregunta> loadAllByIds(String... preguntaIds);

    @Query("SELECT * FROM pregunta_table WHERE _id IN (:preguntaIds)")
    LiveData<Pregunta> FindOne(String preguntaIds);

    // Ejemplo de query para sacar las preguntas de un quiz
    @Query("SELECT * FROM pregunta_table WHERE quizid=:qid")
    List<Pregunta> findRepositoriesForQuiz(String qid);
}