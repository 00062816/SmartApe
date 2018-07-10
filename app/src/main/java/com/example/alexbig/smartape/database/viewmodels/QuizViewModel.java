package com.example.alexbig.smartape.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends AndroidViewModel{

    private static MutableLiveData<List<Quiz>> quizzes = new MutableLiveData<>();

    public QuizViewModel(@NonNull Application application) {
        super(application);
        if (quizzes.getValue() == null) {
            List<Quiz> quizList = new ArrayList<>();
            quizzes.setValue(quizList);
        }
    }

    public MutableLiveData<List<Quiz>> getQuizzes() {
        return quizzes;
    }

    public void insertQuiz(Quiz quiz){
        List<Quiz> quizList = quizzes.getValue();
        quizList.add(quiz);
        quizzes.setValue(quizList);
    }

    public void insertQuizzes(List<Quiz> quizList){
        quizzes.setValue(quizList);
    }
}
