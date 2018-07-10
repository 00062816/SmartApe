package com.example.alexbig.smartape.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.activities.MainActivity;
import com.example.alexbig.smartape.activities.QuizActivity;
import com.example.alexbig.smartape.api.APIRequest;
import com.example.alexbig.smartape.database.entities.QuizEntity;
import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>{

    private Context context;
    private APIRequest apiRequest;
    private List<QuizEntity> quizList = new ArrayList<>();

    public QuizAdapter(Context context, APIRequest apiRequest){
        this.context = context;
        this.apiRequest = apiRequest;
    }

    public void setQuizList(List<QuizEntity> quizList){
        if (quizList != null) {
            this.quizList = quizList;
            this.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_quiz, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.QuizViewHolder holder, int position) {
        final QuizEntity quiz = quizList.get(position);
        holder.titleTextView.setText(quiz.getTitulo());
        holder.descriptionTextView.setText(quiz.getDescripcion());
        holder.userTextView.setText(quiz.getCreador());
        holder.userTextView.setVisibility(View.GONE);

        final CheckBox favoriteButton = holder.favoriteButton;
       /* if (quiz.isFavorite()){
            favoriteButton.setChecked(true);
        }else{
            favoriteButton.setChecked(false);
        }
        favoriteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (favoriteButton.isChecked()){
                    quiz.setFavorite(true);
                }else{
                    quiz.setFavorite(false);
                }
            }
        });

        final CheckBox saveButton = holder.saveButton;
        if (quiz.isSaved()){
            saveButton.setChecked(true);
        }else{
            saveButton.setChecked(false);
        }

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (saveButton.isChecked()){
                    quiz.setSaved(true);
                }else{
                    quiz.setSaved(false);
                }
            }
        });*/

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("QUIZ", quiz.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (quizList != null){
            return quizList.size();
        }else {
            return 0;
        }
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder{

        private ImageView userImageView;
        private TextView userTextView;
        private TextView usernameTextView;
        private TextView dateTextView;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private CheckBox saveButton;
        private CheckBox favoriteButton;

        public QuizViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.imageView_recycler_userPicture);
            userTextView = itemView.findViewById(R.id.textView_recycler_userName);
            usernameTextView = itemView.findViewById(R.id.textView_recycler_userUsername);
            dateTextView = itemView.findViewById(R.id.textView_recycler_quizDate);
            titleTextView = itemView.findViewById(R.id.textView_recycler_quizTitle);
            descriptionTextView = itemView.findViewById(R.id.textView_recycler_quizDescription);
            saveButton = itemView.findViewById(R.id.imageButton_recycler_quizSaved);
            favoriteButton = itemView.findViewById(R.id.imageButton_recycler_quizFavorite);
        }
    }
}
