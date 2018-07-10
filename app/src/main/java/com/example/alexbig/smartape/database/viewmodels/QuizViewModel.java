package com.example.alexbig.smartape.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.alexbig.smartape.database.entities.QuizEntity;
import com.example.alexbig.smartape.database.repositories.QuizRepository;
import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends AndroidViewModel{

    private QuizRepository quizRepository;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        quizRepository = new QuizRepository(application);
    }



    public void insert(QuizEntity quizEntity){
        quizRepository.insert(quizEntity);
    }


    public void insertList(List<QuizEntity> quizEntity){
        quizRepository.insertList(quizEntity);
    }

    public LiveData<List<QuizEntity>> getAllQuizzes(){
        return quizRepository.getAllQuizzes();
    }

    public LiveData<List<QuizEntity>> getQuizzesById(String id){
        return quizRepository.getQuizzesById(id);
    }

}
