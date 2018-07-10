package com.example.alexbig.smartape.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.QuestionAdapter;
import com.example.alexbig.smartape.database.viewmodels.QuestionViewModel;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.models.Question;
import com.example.alexbig.smartape.models.Quiz;
import com.example.alexbig.smartape.utils.Toaster;

import java.util.List;

public class AddQuestionsActivity extends AppCompatActivity{

    public static QuestionViewModel questionViewModel;
    public static QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_question);

        Quiz quiz = (Quiz)getIntent().getSerializableExtra("QUIZ");
        QuizViewModel quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_addQuestion_questions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        questionAdapter = new QuestionAdapter(this) {
            @Override
            public void onClickEdit(int position) {
                Intent intent = new Intent(getApplicationContext(), CreateQuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("QUESTION", questionViewModel.getQuestions().getValue().get(position));
                bundle.putInt("POSITION", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };

        recyclerView.setAdapter(questionAdapter);
        recyclerView.setHasFixedSize(true);

        FloatingActionButton addButton = findViewById(R.id.floatingActionButton_addQuestion_add);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateQuestionActivity.class);
                startActivity(intent);
            }
        });

        Button createButton = findViewById(R.id.button_addQuestion_nextScreen);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (questionViewModel.getQuestions().getValue().isEmpty()){
                    Toaster.makeToast(getApplicationContext(), "Quiz must have at least one question");
                    return;
                }

                quiz.setQuestions(questionViewModel.getQuestions().getValue());
                //quizViewModel.insertQuiz(quiz);
                finish();
            }
        });

        questionViewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questionList) {
                if (questionList != null) {
                    questionAdapter.setQuestionList(questionList);
                }
            }
        });
    }
}
