package com.example.alexbig.smartape.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.alexbig.smartape.database.daos.PreguntaDao;
import com.example.alexbig.smartape.database.daos.QuizDao;
import com.example.alexbig.smartape.database.daos.ResueltoDao;
import com.example.alexbig.smartape.database.daos.SolucionDao;
import com.example.alexbig.smartape.database.daos.UsuarioDao;

import com.example.alexbig.smartape.database.entities.PreguntaEntity;
import com.example.alexbig.smartape.database.entities.QuizEntity;
import com.example.alexbig.smartape.database.entities.Resuelto;
import com.example.alexbig.smartape.database.entities.Solucion;
import com.example.alexbig.smartape.database.entities.Usuario;



/**
 * Created by Andres on 4/7/2018.
 */


@Database(entities = {PreguntaEntity.class, QuizEntity.class, Resuelto.class, Solucion.class, Usuario.class}, version = 1)


public abstract class ApesDataBase extends RoomDatabase {
    private static final String DB_NAME = "SmartApes.db";
    private static volatile ApesDataBase instance;

    public static synchronized ApesDataBase getInstance(Context context){
        if (instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static ApesDataBase create(Context context) {
        return Room.databaseBuilder(
                context,
                ApesDataBase.class,
                DB_NAME
        ).build();
    }

    public abstract PreguntaDao preguntaDao();
    public abstract QuizDao quizDao();
    public abstract ResueltoDao resueltoDao();
    public abstract SolucionDao solucionDao();
    public abstract UsuarioDao usuarioDao();
}