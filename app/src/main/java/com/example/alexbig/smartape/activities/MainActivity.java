package com.example.alexbig.smartape.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.ViewPagerAdapter;
import com.example.alexbig.smartape.fragments.QuizListFragment;
import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private QuizListFragment quizListFragment;

    public static List<Quiz> quizList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTabs();
    }

    private void setTabs(){
        fragmentManager = getSupportFragmentManager();
        quizListFragment = new QuizListFragment();

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.addFragment(quizListFragment, getString(R.string.tab_quizzes));
        tabLayout.setupWithViewPager(viewPager);

        Quiz quiz1 = new Quiz();
        quiz1.setTitle("Quiz 1");
        quiz1.setDescription("Quiz description");
        quizList.add(quiz1);
        Quiz quiz2 = new Quiz();
        quiz2.setTitle("Quiz 2");
        quiz2.setDescription("Quiz description");
        quizList.add(quiz2);
        Quiz quiz3 = new Quiz();
        quiz3.setTitle("Quiz 3");
        quiz3.setDescription("Quiz description");
        quizList.add(quiz3);
    }
}
