package bigdreams.example.alexbig.smartape.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import bigdreams.example.alexbig.smartape.R;
import bigdreams.example.alexbig.smartape.api.APIRequest;
import bigdreams.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import bigdreams.example.alexbig.smartape.models.Quiz;
import bigdreams.example.alexbig.smartape.utils.Toaster;

import java.util.ArrayList;

import bigdreams.example.alexbig.smartape.api.APIRequest;
import bigdreams.example.alexbig.smartape.models.Quiz;
import bigdreams.example.alexbig.smartape.utils.Toaster;

public class CreateQuizActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bigdreams.example.alexbig.smartape.R.layout.layout_create_quiz);

        final EditText titleEditText = findViewById(bigdreams.example.alexbig.smartape.R.id.editText_createQuiz_quizName);
        final EditText descriptionEditText = findViewById(bigdreams.example.alexbig.smartape.R.id.editText_createQuiz_quizDescription);
        final Spinner categorySpinner = findViewById(bigdreams.example.alexbig.smartape.R.id.spinner_createQuiz_quizCategory);
        Button createButton = findViewById(bigdreams.example.alexbig.smartape.R.id.button_createQuiz);

        final Quiz quiz = new Quiz();
        final QuizViewModel quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        APIRequest apiRequest = new APIRequest(this, quizViewModel);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleEditText.getText().toString().equals("")){
                    Toaster.makeToast(getApplicationContext(), getString(bigdreams.example.alexbig.smartape.R.string.text_empty_title_error));
                    return;
                }

                quiz.setTitle(titleEditText.getText().toString());
                quiz.setStatus(0);
                quiz.setDescription(descriptionEditText.getText().toString());
                quiz.setCategory(categorySpinner.getSelectedItem().toString());
                quiz.setTimeLimit("0:00");

                //apiRequest.uploadQuiz(quiz);
                Intent intent = new Intent(getApplicationContext(), AddQuestionsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("QUIZ", quiz);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}