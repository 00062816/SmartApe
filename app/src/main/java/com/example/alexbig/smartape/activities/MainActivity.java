package com.example.alexbig.smartape.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.ViewPagerAdapter;
import com.example.alexbig.smartape.api.APIRequest;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.fragments.QuizListFragment;
import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private QuizListFragment quizListFragment;
    private ViewPagerAdapter viewPagerAdapter;
    private FloatingActionButton addFAB;
    private APIRequest apiRequest;
    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("logged", MODE_PRIVATE);
        Intent intentL = new Intent(this, LoginActivity.class);

        if (sharedPreferences.contains("token")){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTabs();
            setDrawer();

        }else {
            startActivity(intentL);
            finish();
        }
        /*quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        apiRequest = new APIRequest(quizViewModel);
        apiRequest.login("uca@edu.sv","chaleco234");*/
    }

    private void setTabs() {
        fragmentManager = getSupportFragmentManager();
        quizListFragment = new QuizListFragment();
        quizListFragment.setApiRequest(apiRequest);

        addFAB = findViewById(R.id.addFAB);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.addFragment(quizListFragment, getString(R.string.tab_quizzes));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setDrawer() {
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout_main);
        NavigationView navigationView = findViewById(R.id.navigationView_main);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_quizzes_item:
                        openQuizzes();
                        break;

                    case R.id.menu_favorite_item:
                        openFavorites();
                        break;

                    case R.id.menu_saved_item:
                        openSaved();
                        break;

                    case R.id.menu_myquizzes_item:
                        openMyQuizzes();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void openQuizzes(){
        addFAB.setVisibility(View.INVISIBLE);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        quizListFragment.sortAll();
    }

    private void openFavorites() {
        addFAB.setVisibility(View.INVISIBLE);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        quizListFragment.sortFavorites();
    }

    private void openSaved() {
        addFAB.setVisibility(View.INVISIBLE);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        quizListFragment.sortSaved();
    }

    private void openMyQuizzes() {
        addFAB.setVisibility(View.VISIBLE);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quiz quiz = new Quiz();
                quiz.setTitle("New Quiz");
                quiz.setDescription("Quiz description");
                quiz.setCreator("USER");
                quizViewModel.insertQuiz(quiz);
            }
        });
        quizListFragment.sortMyQuizzes();
    }

    private void refresh(){
        apiRequest.refresh();
    }
}
