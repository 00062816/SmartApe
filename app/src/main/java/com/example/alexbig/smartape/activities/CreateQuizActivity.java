package com.example.alexbig.smartape.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.models.Quiz;

public class CreateQuizActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_quiz);

        EditText titleEditText = findViewById(R.id.editText_createQuiz_quizName);
        EditText decriptionEditText = findViewById(R.id.editText_createQuiz_quizDescription);
        Spinner categorySpinner = findViewById(R.id.spinner_createQuiz_quizCategory);
        Button createButton = findViewById(R.id.button_createQuiz);

        Quiz quiz = new Quiz();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
