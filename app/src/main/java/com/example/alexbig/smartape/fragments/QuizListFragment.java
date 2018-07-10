package com.example.alexbig.smartape.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.QuizAdapter;
import com.example.alexbig.smartape.api.APIRequest;
import com.example.alexbig.smartape.database.entities.QuizEntity;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizListFragment extends Fragment{

    private QuizAdapter quizAdapter;
    private APIRequest apiRequest;
    private QuizViewModel quizViewModel;
    private List<QuizEntity> quizEntityList = new ArrayList<>();

    public static QuizListFragment newInstance(int type){
        Bundle arguments = new Bundle();
        arguments.putInt("type", type);

        QuizListFragment quizListFragment = new QuizListFragment();
        quizListFragment.setArguments(arguments);
        return quizListFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_dashboard, container, false);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        apiRequest = new APIRequest(getContext());
        apiRequest.downloadQuizzes(quizViewModel);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_dashboard_allQuizzes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        quizAdapter = new QuizAdapter(container.getContext(), apiRequest);
        recyclerView.setAdapter(quizAdapter);
        recyclerView.setHasFixedSize(true);

        quizViewModel.getAllQuizzes().observe(this, new Observer<List<QuizEntity>>() {
            @Override
            public void onChanged(@Nullable List<QuizEntity> quizEntities) {
                quizAdapter.setQuizList(quizEntities);
            }
        });

        return view;
    }

    public void setApiRequest(APIRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    public void setQuizList(List<QuizEntity> quizList){
        quizAdapter.setQuizList(quizList);
    }

    public List<QuizEntity> filterQuizzes(int type){
       switch (type){
           case 1:
               apiRequest.downloadQuizzes(quizViewModel);
               break;
           case 2:
                apiRequest.downloadFavedQuizzes(quizViewModel);
               break;
           case 3:
               apiRequest.downloadSavedQuizzes(quizViewModel);
               break;
           case 4:
               apiRequest.downloadCreatedQuizzes(quizViewModel);
               break;
       }

        return quizEntityList;
    }
}
