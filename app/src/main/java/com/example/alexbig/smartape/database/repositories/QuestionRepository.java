package com.example.alexbig.smartape.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.alexbig.smartape.database.ApesDataBase;
import com.example.alexbig.smartape.database.daos.PreguntaDao;
import com.example.alexbig.smartape.database.daos.QuizDao;
import com.example.alexbig.smartape.database.entities.PreguntaEntity;
import com.example.alexbig.smartape.database.entities.QuizEntity;

import java.util.List;

public class QuestionRepository {

    private PreguntaDao preguntaDao;

    public QuestionRepository(Application application) {
        ApesDataBase dataBase = ApesDataBase.getInstance(application);
        preguntaDao = dataBase.preguntaDao();

    }

    public LiveData<List<PreguntaEntity>> getAllPreguntas(){
        return preguntaDao.getAllPreguntas();
    }

    public LiveData<List<PreguntaEntity>> getAllPreguntasById(){
        return preguntaDao.getAllPreguntas();
    }

    public void insert(PreguntaEntity preguntaEntity){
        new InsertAsyncTask(preguntaDao).execute(preguntaEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<PreguntaEntity, Void, Void>{

        private PreguntaDao myAsyncTaskPreguntaDao;

        InsertAsyncTask(PreguntaDao dao){
            myAsyncTaskPreguntaDao = dao;
        }

        @Override
        protected Void doInBackground(PreguntaEntity... preguntaEntities) {
            myAsyncTaskPreguntaDao.DeleteAllPreguntas();
            myAsyncTaskPreguntaDao.insert(preguntaEntities);
            return null;
        }
    }

    public void insertList(List<PreguntaEntity> quizEntity){
        new InsertListAsyncTask(preguntaDao).execute(quizEntity);
    }

    private static class InsertListAsyncTask extends AsyncTask<List<PreguntaEntity>, Void, Void>{

        private PreguntaDao myAsyncTaskPreguntaDao;

        InsertListAsyncTask(PreguntaDao dao){
            myAsyncTaskPreguntaDao = dao;
        }

        @Override
        protected Void doInBackground(List<PreguntaEntity>... preguntaEntities) {
            myAsyncTaskPreguntaDao.DeleteAllPreguntas();
        for(PreguntaEntity e : preguntaEntities[0])
            myAsyncTaskPreguntaDao.insert(e);
            return null;
        }
    }

}

