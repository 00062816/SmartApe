package bigdreams.example.alexbig.smartape.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import bigdreams.example.alexbig.smartape.models.Question;

import java.util.ArrayList;
import java.util.List;

import bigdreams.example.alexbig.smartape.models.Question;

public class QuestionViewModel extends AndroidViewModel{

    private MutableLiveData<List<Question>> questions = new MutableLiveData<>();

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        List<Question> questionList = new ArrayList<>();
        questions.setValue(questionList);
    }

    public MutableLiveData<List<Question>> getQuestions() {
        return questions;
    }

    public void insertQuestion(Question question){
        List<Question> questionList = questions.getValue();
        questionList.add(question);
        questions.setValue(questionList);
    }

    public void setQuestions(List<Question> questionList){
        questions.setValue(questionList);
    }
}