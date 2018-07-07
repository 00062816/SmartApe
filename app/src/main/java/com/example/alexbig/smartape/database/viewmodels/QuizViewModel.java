package com.example.alexbig.smartape.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends AndroidViewModel{

    private MutableLiveData<List<Quiz>> quizzes = new MutableLiveData<>();

    public QuizViewModel(@NonNull Application application) {
        super(application);
        List<Quiz> quizList = new ArrayList<>();
        quizzes.setValue(quizList);
    }

    public MutableLiveData<List<Quiz>> getQuizzes() {
        return quizzes;
    }

    public void insertQuiz(Quiz quiz){
        quizzes.getValue().add(quiz);
    }

    public void insertQuizzes(List<Quiz> quizList){
        quizzes.getValue().addAll(quizList);
    }
}
