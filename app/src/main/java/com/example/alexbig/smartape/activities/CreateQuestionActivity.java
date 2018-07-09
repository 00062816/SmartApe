package com.example.alexbig.smartape.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.AnswerAdapter;
import com.example.alexbig.smartape.database.viewmodels.AnswerViewModel;
import com.example.alexbig.smartape.models.Answer;
import com.example.alexbig.smartape.models.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.layout_create_question);

        Question question = new Question();
        AnswerViewModel answerViewModel = ViewModelProviders.of(this).get(AnswerViewModel.class);

        EditText questionEditText = findViewById(R.id.edittext_createQuestion_premise);
        Spinner typeSpinner = findViewById(R.id.spinner_createQuestion_category);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_createQuestion_answers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        AnswerAdapter adapter = new AnswerAdapter(this);
        adapter.setAnswerList(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        FloatingActionButton addAnswer = findViewById(R.id.floatingActionButton_createQuestion_addAnswer);
        addAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.getAnswerList().add(new Answer());
                adapter.notifyDataSetChanged();
            }
        });

        Button createQuestion = findViewById(R.id.button_createQuestion_create);
        createQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                question.setPremise(questionEditText.getText().toString());
                question.setAnswers(adapter.getAnswerList());

                for (Answer a:adapter.getAnswerList()){
                    System.out.println("ANSWER "+a.getText());
                }

                AddQuestionsActivity.questionViewModel.insertQuestion(question);
                finish();
            }
        });

        answerViewModel.getAnswers().observe(this, new Observer<List<Answer>>() {
            @Override
            public void onChanged(@Nullable List<Answer> answers) {
                //adapter.setAnswerList(answers);
            }
        });
    }
}
