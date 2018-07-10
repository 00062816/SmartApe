package com.example.alexbig.smartape.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.alexbig.smartape.api.SmartApeAPI;
import com.example.alexbig.smartape.api.deserializers.QuizDeserializer;
import com.example.alexbig.smartape.database.ApesDataBase;
import com.example.alexbig.smartape.database.daos.QuizDao;
import com.example.alexbig.smartape.database.entities.QuizEntity;
import com.example.alexbig.smartape.models.Quiz;
import com.example.alexbig.smartape.utils.ActivityManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class QuizRepository {

    private QuizDao quizDao;

    public QuizRepository(Application application) {
        ApesDataBase dataBase = ApesDataBase.getInstance(application);
        quizDao = dataBase.quizDao();

    }

    public LiveData<List<QuizEntity>> getAllQuizzes(){
        return quizDao.getAllQuiz();
    }

    public void insert(QuizEntity quizEntity){
        new InsertAsyncTask(quizDao).execute(quizEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<QuizEntity, Void, Void>{

        private QuizDao myAsyncTaskQuizDao;

        InsertAsyncTask(QuizDao dao){
            myAsyncTaskQuizDao = dao;
        }

        @Override
        protected Void doInBackground(QuizEntity... quizEntities) {
            myAsyncTaskQuizDao.insert(quizEntities);
            return null;
        }
    }


}

