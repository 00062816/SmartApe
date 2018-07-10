package com.example.alexbig.smartape.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.alexbig.smartape.database.entities.PreguntaEntity;
import com.example.alexbig.smartape.database.entities.QuizEntity;
import com.example.alexbig.smartape.database.repositories.QuestionRepository;
import com.example.alexbig.smartape.database.repositories.QuizRepository;

import java.util.List;

public class PreguntaViewModel extends AndroidViewModel{

    private QuestionRepository preguntarepository;

    public PreguntaViewModel(@NonNull Application application) {
        super(application);
        preguntarepository = new QuestionRepository(application);
    }


    public void insert(PreguntaEntity preguntaEntity){
        preguntarepository.insert(preguntaEntity);
    }


    public void insertList(List<PreguntaEntity> preguntaEntity){
        preguntarepository.insertList(preguntaEntity);
    }

    public LiveData<List<PreguntaEntity>> getAllPreguntas(){
        return preguntarepository.getAllPreguntas();
    }

    /*public LiveData<List<PreguntaEntity>> getAllPreguntasByid(){
        return preguntarepository.getAllPreguntasByid();
    }*/

}
