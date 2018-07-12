package bigdreams.example.alexbig.smartape.activities;

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

import bigdreams.example.alexbig.smartape.R;
import bigdreams.example.alexbig.smartape.adapters.QuestionAdapter;
import bigdreams.example.alexbig.smartape.database.viewmodels.QuestionViewModel;
import bigdreams.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import bigdreams.example.alexbig.smartape.models.Question;
import bigdreams.example.alexbig.smartape.models.Quiz;
import bigdreams.example.alexbig.smartape.utils.Toaster;

import java.util.List;

import bigdreams.example.alexbig.smartape.adapters.QuestionAdapter;
import bigdreams.example.alexbig.smartape.models.Question;
import bigdreams.example.alexbig.smartape.models.Quiz;
import bigdreams.example.alexbig.smartape.utils.Toaster;

public class AddQuestionsActivity extends AppCompatActivity{

    public static QuestionViewModel questionViewModel;
    public static QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bigdreams.example.alexbig.smartape.R.layout.layout_add_question);

        Quiz quiz = (Quiz)getIntent().getSerializableExtra("QUIZ");
        QuizViewModel quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        RecyclerView recyclerView = findViewById(bigdreams.example.alexbig.smartape.R.id.recyclerView_addQuestion_questions);
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

        FloatingActionButton addButton = findViewById(bigdreams.example.alexbig.smartape.R.id.floatingActionButton_addQuestion_add);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateQuestionActivity.class);
                startActivity(intent);
            }
        });

        Button createButton = findViewById(bigdreams.example.alexbig.smartape.R.id.button_addQuestion_nextScreen);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (questionViewModel.getQuestions().getValue().isEmpty()){
                    Toaster.makeToast(getApplicationContext(), getString(bigdreams.example.alexbig.smartape.R.string.text_empty_questions_error));
                    return;
                }

                quiz.setQuestions(questionViewModel.getQuestions().getValue());
                quizViewModel.insertQuiz(quiz);
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
