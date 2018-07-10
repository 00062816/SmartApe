package com.example.alexbig.smartape.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.models.Question;

import java.util.List;

public abstract class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{

    private Context context;
    private List<Question> questionList;

    public QuestionAdapter(Context context){
        this.context = context;
    }

    public void setQuestionList(List<Question> questionList){
        this.questionList = questionList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_created_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.questionTextView.setText(question.getPremise());

        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                questionList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onClickEdit(position);
            }
        });
    }

    public abstract void onClickEdit(int position);

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{

        private ImageButton deleteButton;
        private ImageButton editButton;
        private TextView questionTextView;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.textView_recyclerAddQuestion_question);
            deleteButton = itemView.findViewById(R.id.imageButton_recyclerAddQuestion_deleteQuestion);
            editButton = itemView.findViewById(R.id.imageButton_recyclerAddQuestion_editQuestion);
        }
    }
}
