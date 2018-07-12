package bigdreams.example.alexbig.smartape.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import bigdreams.example.alexbig.smartape.R;
import bigdreams.example.alexbig.smartape.adapters.AnswerAdapter;
import bigdreams.example.alexbig.smartape.database.viewmodels.AnswerViewModel;
import bigdreams.example.alexbig.smartape.models.Answer;
import bigdreams.example.alexbig.smartape.models.Question;
import bigdreams.example.alexbig.smartape.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import bigdreams.example.alexbig.smartape.adapters.AnswerAdapter;
import bigdreams.example.alexbig.smartape.models.Answer;
import bigdreams.example.alexbig.smartape.models.Question;
import bigdreams.example.alexbig.smartape.utils.Toaster;

public class CreateQuestionActivity extends AppCompatActivity{

    private AnswerViewModel answerViewModel;
    private AnswerAdapter adapter;
    private Question question;
    private boolean edit = false;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(bigdreams.example.alexbig.smartape.R.layout.layout_create_question);

        answerViewModel = ViewModelProviders.of(this).get(AnswerViewModel.class);

        EditText questionEditText = findViewById(bigdreams.example.alexbig.smartape.R.id.edittext_createQuestion_premise);

        RecyclerView recyclerView = findViewById(bigdreams.example.alexbig.smartape.R.id.recyclerView_createQuestion_answers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AnswerAdapter(this) {
            @Override
            public void onItemClick(View v, int position) {
                showAnswerDialog(getAnswerList().get(position));
            }
        };
        adapter.setAnswerList(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.hasExtra("QUESTION")){
            question = (Question) intent.getExtras().getSerializable("QUESTION");
            position = intent.getExtras().getInt("POSITION");
            questionEditText.setText(question.getPremise());
            answerViewModel.setAnswers(question.getAnswers());
            edit = true;
        }else{
            question = new Question();
        }

        FloatingActionButton addAnswer = findViewById(bigdreams.example.alexbig.smartape.R.id.floatingActionButton_createQuestion_addAnswer);
        addAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswerDialog();
            }
        });

        Button createQuestion = findViewById(bigdreams.example.alexbig.smartape.R.id.button_createQuestion_create);
        if (edit){
           createQuestion.setText(bigdreams.example.alexbig.smartape.R.string.text_update_question);
        }
        createQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (questionEditText.getText().toString().equals("")){
                    Toaster.makeToast(getApplicationContext(), getString(bigdreams.example.alexbig.smartape.R.string.text_empty_premise_error));
                    return;
                }
                if (adapter.getAnswerList().isEmpty()){
                    Toaster.makeToast(getApplicationContext(), getString(bigdreams.example.alexbig.smartape.R.string.text_empty_answers_error));
                    return;
                }

                question.setPremise(questionEditText.getText().toString());
                question.setAnswers(adapter.getAnswerList());

                if (!edit) {
                    AddQuestionsActivity.questionViewModel.insertQuestion(question);
                }else{
                    Question newQuestion = AddQuestionsActivity.questionViewModel.getQuestions().getValue().get(position);
                    newQuestion.setPremise(question.getPremise());
                    newQuestion.setAnswers(question.getAnswers());
                    AddQuestionsActivity.questionAdapter.notifyDataSetChanged();
                }
                finish();
            }
        });

        answerViewModel.getAnswers().observe(this, new Observer<List<Answer>>() {
            @Override
            public void onChanged(@Nullable List<Answer> answers) {
                adapter.setAnswerList(answers);
            }
        });
    }

    private void showAnswerDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(bigdreams.example.alexbig.smartape.R.string.text_new_answer);

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton(bigdreams.example.alexbig.smartape.R.string.text_ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (input.getText().toString().equals("")){
                    Toaster.makeToast(getApplicationContext(), getString(bigdreams.example.alexbig.smartape.R.string.text_empty_answer_error));
                    return;
                }

                Answer answer = new Answer();
                answer.setText(input.getText().toString());
                answerViewModel.insertAnswer(answer);
            }
        });
        builder.setNegativeButton(bigdreams.example.alexbig.smartape.R.string.text_cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private void showAnswerDialog(Answer answer){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(bigdreams.example.alexbig.smartape.R.string.text_edit_answer);

        final EditText input = new EditText(this);
        input.setText(answer.getText());
        builder.setView(input);

        builder.setPositiveButton(bigdreams.example.alexbig.smartape.R.string.text_ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (input.getText().toString().equals("")){
                    Toaster.makeToast(getApplicationContext(), getString(bigdreams.example.alexbig.smartape.R.string.text_empty_answer_error));
                    return;
                }

                answer.setText(input.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(bigdreams.example.alexbig.smartape.R.string.text_remove_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                answerViewModel.deleteAnswer(answer);
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
}
