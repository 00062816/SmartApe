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

import com.example.alexbig.smartape.database.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Usuario... users);

    @Delete
    void delete(Usuario user);

    @Update
    void update(Usuario... users);

    @Query("DELETE FROM usuario_table")
    void DeleteAllUserios();

    @Query("SELECT * FROM usuario_table WHERE _id IN (:usuarioIds)")
    List<Usuario> loadAllByIds(String... usuarioIds);

    @Query("SELECT * FROM usuario_table WHERE _id IN (:usuarioIds)")
    LiveData<Usuario> FindOne(String usuarioIds);
}