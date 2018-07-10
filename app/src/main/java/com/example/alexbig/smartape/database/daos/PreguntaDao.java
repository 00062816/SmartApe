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

<<<<<<< HEAD
import com.example.alexbig.smartape.database.entities.PreguntaEntity;
=======
import com.example.alexbig.smartape.database.objects.PreguntaEntity;
>>>>>>> master

import java.util.List;

@Dao
public interface PreguntaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
<<<<<<< HEAD
    void insert(PreguntaEntity... preguntas);

    @Delete
    void delete(PreguntaEntity pregunta);

    @Update
    void update(PreguntaEntity... preguntas);
=======
    void insert(PreguntaEntity... preguntaEntities);

    @Delete
    void delete(PreguntaEntity preguntaEntity);

    @Update
    void update(PreguntaEntity... preguntaEntities);
>>>>>>> master

    @Query("DELETE FROM pregunta_table")
    void DeleteAllPreguntas();

    @Query("SELECT * FROM pregunta_table")
    LiveData<List<PreguntaEntity>> getAllPreguntas();

    @Query("SELECT * FROM pregunta_table WHERE _id IN (:preguntaIds)")
    List<PreguntaEntity> loadAllByIds(String... preguntaIds);

    @Query("SELECT * FROM pregunta_table WHERE _id IN (:preguntaIds)")
    LiveData<PreguntaEntity> FindOne(String preguntaIds);

    // Ejemplo de query para sacar las preguntas de un quiz
    @Query("SELECT * FROM pregunta_table WHERE quizid=:qid")
    List<PreguntaEntity> findRepositoriesForQuiz(String qid);
}