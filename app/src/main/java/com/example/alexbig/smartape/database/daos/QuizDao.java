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


import com.example.alexbig.smartape.database.entities.QuizEntity;

import java.util.List;

@Dao
public interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuizEntity... quizEntities);

    @Delete
    void delete(QuizEntity quizEntity);

    @Update
    void update(QuizEntity... quizEntities);

    @Query("DELETE FROM quiz_table")
    void DeleteAllQuiz();

    @Query("SELECT * FROM quiz_table")
    LiveData<List<QuizEntity>> getAllQuiz();

    @Query("SELECT * FROM quiz_table WHERE _id IN (:quizIds)")
    LiveData<List<QuizEntity>> loadAllByIds(String... quizIds);

    @Query("SELECT * FROM quiz_table WHERE _id IN (:quizIds)")
    LiveData<QuizEntity> FindOne(String quizIds);

    // Ejemplo de query para sacar quiz con referencia del usuario
    @Query("SELECT * FROM quiz_table WHERE Creador=:userId")
    LiveData<List<QuizEntity>> findRepositoriesForUser(String userId);
}