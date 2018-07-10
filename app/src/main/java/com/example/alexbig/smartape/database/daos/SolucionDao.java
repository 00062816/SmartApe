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
import com.example.alexbig.smartape.database.entities.Solucion;
=======
import com.example.alexbig.smartape.database.objects.SolucionEntity;
>>>>>>> master

import java.util.List;

@Dao
public interface SolucionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SolucionEntity... solucionEntities);

    @Delete
    void delete(SolucionEntity solucionEntity);

    @Update
    void update(SolucionEntity... solucionEntities);

    @Query("DELETE FROM solucion_table")
    void DeleteAllSolucion();

    @Query("SELECT * FROM solucion_table WHERE _id IN (:solucionIds)")
    List<SolucionEntity> loadAllByIds(String... solucionIds);

    @Query("SELECT * FROM solucion_table WHERE _id IN (:solucionIds)")
    LiveData<SolucionEntity> FindOne(String solucionIds);

    // Ejemplo de query para sacar las soluciones de un quiz
    @Query("SELECT * FROM solucion_table WHERE preguntaid=:sid")
    List<SolucionEntity> findRepositoriesForPregunta(String sid);
}