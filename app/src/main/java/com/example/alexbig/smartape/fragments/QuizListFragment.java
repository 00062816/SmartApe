package com.example.alexbig.smartape.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.QuizAdapter;
import com.example.alexbig.smartape.models.Quiz;

import java.util.List;

public class QuizListFragment extends Fragment{

    private QuizAdapter quizAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_all_quizzes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_main_allQuizzes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        quizAdapter = new QuizAdapter(container.getContext());
        recyclerView.setAdapter(quizAdapter);
        recyclerView.setHasFixedSize(true);

        return view;
    }

    public void setQuizList(List<Quiz> quizList){
        quizAdapter.setQuizList(quizList);
    }
}
