package com.example.alexbig.smartape.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.alexbig.smartape.models.Answer;
import com.example.alexbig.smartape.models.Question;

import java.util.ArrayList;
import java.util.List;

public class AnswerViewModel extends AndroidViewModel{

    private MutableLiveData<List<Answer>> answers = new MutableLiveData<>();

    public AnswerViewModel(@NonNull Application application) {
        super(application);
        List<Answer> answerList = new ArrayList<>();
        answers.setValue(answerList);
    }

    public MutableLiveData<List<Answer>> getAnswers() {
        return answers;
    }

    public void insertAnswer(Answer answer){
        List<Answer> answerList = answers.getValue();
        answerList.add(answer);
        answers.setValue(answerList);
    }

    public void setAnswers(List<Answer> answerList){
        this.answers.setValue(answerList);
    }

    public void deleteAnswer(Answer answer){
        List<Answer> answerList = answers.getValue();
        answerList.remove(answer);
        answers.setValue(answerList);
    }
}