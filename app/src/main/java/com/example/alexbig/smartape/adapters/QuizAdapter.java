package com.example.alexbig.smartape.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.activities.MainActivity;
import com.example.alexbig.smartape.models.Quiz;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>{

    private Context context;
    private List<Quiz> quizList;

    public QuizAdapter(Context context){
        this.context = context;
        quizList = MainActivity.quizList;
    }

    public void setQuizList(List<Quiz> quizList){
        this.quizList = quizList;
        this.notifyDataSetChanged();
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
        Quiz quiz = quizList.get(position);
        holder.titleTextView.setText(quiz.getTitle());
        holder.descriptionTextView.setText(quiz.getDescription());
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder{

        private ImageView userImageView;
        private TextView userTextView;
        private TextView usernameTextView;
        private TextView dateTextView;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageButton saveButton;
        private ImageButton favoriteButton;

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
