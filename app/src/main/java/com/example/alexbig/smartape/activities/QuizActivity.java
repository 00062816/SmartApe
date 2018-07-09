package com.example.alexbig.smartape.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.api.APIRequest;
import com.example.alexbig.smartape.database.viewmodels.QuestionViewModel;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.models.Question;
import com.example.alexbig.smartape.models.Quiz;

import java.util.List;

public class QuizActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.layout_answer_question);


        Intent intent = getIntent();
        Quiz quiz = (Quiz)intent.getSerializableExtra("QUIZ");

        QuizViewModel quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        QuestionViewModel questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        APIRequest apiRequest = new APIRequest(this, quizViewModel);

        apiRequest.downloadQuestions(questionViewModel, quiz);

        TextView questionTextView = findViewById(R.id.textView_answerQuestion_question);

        questionViewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                if (questions != null) {
                    System.out.println(questions.toString());
                    //questionTextView.setText(questions.get(0).getPremise());
                }
            }
        });
    }
}
