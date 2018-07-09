package com.example.alexbig.smartape.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.AnswerAdapter;
import com.example.alexbig.smartape.api.APIRequest;
import com.example.alexbig.smartape.database.viewmodels.AnswerViewModel;
import com.example.alexbig.smartape.database.viewmodels.QuestionViewModel;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.models.Answer;
import com.example.alexbig.smartape.models.Question;
import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity{

    private List<Question> questions = new ArrayList<>();
    private TextView questionTextView;
    private AnswerViewModel answerViewModel;
    private AnswerAdapter answerAdapter;
    private int counter = 0;

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.layout_answer_question);

        QuizViewModel quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        QuestionViewModel questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        answerViewModel = ViewModelProviders.of(this).get(AnswerViewModel.class);
        APIRequest apiRequest = new APIRequest(this, quizViewModel);

        Intent intent = getIntent();
        Quiz quiz = (Quiz)intent.getSerializableExtra("QUIZ");
        questionViewModel.setQuestions(quiz.getQuestions());

        //apiRequest.downloadQuestions(questionViewModel, quiz);

        questionTextView = findViewById(R.id.textView_answerQuestion_question);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_answerQuestion_answers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        answerAdapter = new AnswerAdapter(this);
        answerAdapter.setAnswerList(new ArrayList<>());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(answerAdapter);
        recyclerView.setHasFixedSize(true);

        Button previous = findViewById(R.id.button_answerQuestion_previousQuestion);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                if (counter >= 0){
                    setQuestion(counter);
                }else{
                    counter = 0;
                }
            }
        });

        Button next = findViewById(R.id.button_answerQuestion_nextQuestion);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                counter++;
                if (counter < questions.size()){
                    setQuestion(counter);
                }else{
                    counter = questions.size()-1;
                }
            }
        });

        questionViewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questionList) {
                questions = questionList;
            }
        });

        answerViewModel.getAnswers().observe(this, new Observer<List<Answer>>() {
            @Override
            public void onChanged(@Nullable List<Answer> answers) {
                answerAdapter.setAnswerList(answers);
            }
        });
    }

    private void setQuestion(int counter){
        questionTextView.setText(questions.get(counter).getPremise());
        answerViewModel.setAnswers(questions.get(counter).getAnswers());
    }
}
