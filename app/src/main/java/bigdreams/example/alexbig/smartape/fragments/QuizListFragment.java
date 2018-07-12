package bigdreams.example.alexbig.smartape.fragments;

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

import bigdreams.example.alexbig.smartape.R;
import bigdreams.example.alexbig.smartape.adapters.QuizAdapter;
import bigdreams.example.alexbig.smartape.api.APIRequest;
import bigdreams.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import bigdreams.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

import bigdreams.example.alexbig.smartape.adapters.QuizAdapter;
import bigdreams.example.alexbig.smartape.api.APIRequest;
import bigdreams.example.alexbig.smartape.models.Quiz;

public class QuizListFragment extends Fragment{

    private QuizAdapter quizAdapter;
    private APIRequest apiRequest;
    private QuizViewModel quizViewModel;
    private List<Quiz> quizList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(bigdreams.example.alexbig.smartape.R.layout.layout_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(bigdreams.example.alexbig.smartape.R.id.recyclerView_dashboard_allQuizzes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        quizAdapter = new QuizAdapter(container.getContext(), apiRequest);
        recyclerView.setAdapter(quizAdapter);
        recyclerView.setHasFixedSize(true);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.getQuizzes().observe(this, new Observer<List<Quiz>>() {
             @Override
             public void onChanged(@Nullable List<Quiz> quizzes) {
                 setQuizList(quizzes);
             }
         });

        return view;
    }

    public void setApiRequest(APIRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    private void setQuizList(List<Quiz> quizList){
        quizAdapter.setQuizList(quizList);
    }

    public void sortAll(){
        setQuizList(quizList);
    }

    public void sortFavorites(){
        List<Quiz> newList = new ArrayList<>();
        for (Quiz q : quizList) {
            if (q.isFavorite()) {
                newList.add(q);
            }
        }
        setQuizList(newList);
    }

    public void sortSaved(){
        List<Quiz> newList = new ArrayList<>();
        for (Quiz q : quizList) {
            if (q.isSaved()) {
                newList.add(q);
            }
        }
        setQuizList(newList);
    }

    public void sortMyQuizzes(){
        List<Quiz> newList = new ArrayList<>();
        for (Quiz q : quizList) {
            if (q.getCreator().equals("USER")) {
                newList.add(q);
            }
        }
        setQuizList(newList);
    }
}
