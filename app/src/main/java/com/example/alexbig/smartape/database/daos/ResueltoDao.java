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

import com.example.alexbig.smartape.database.objects.Resuelto;

import java.util.List;

@Dao
public interface ResueltoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Resuelto... resueltos);

    @Delete
    void delete(Resuelto resuelto);

    @Update
    void update(Resuelto... resueltos);

    @Query("DELETE FROM resuelto_table")
    void DeleteAllPreguntas();

    @Query("SELECT * FROM resuelto_table WHERE _id IN (:resuletoIds)")
    List<Resuelto> loadAllByIds(String... resuletoIds);

    @Query("SELECT * FROM resuelto_table WHERE _id IN (:resuletoIds)")
    LiveData<Resuelto> FindOne(String resuletoIds);

    //
    @Query("SELECT * FROM resuelto_table WHERE quizid=:qid")
    List<Resuelto> findRepositoriesForQuiz(String qid);

    //
    @Query("SELECT * FROM resuelto_table WHERE usuarioid=:uid")
    List<Resuelto> findRepositoriesForUser(String uid);
}