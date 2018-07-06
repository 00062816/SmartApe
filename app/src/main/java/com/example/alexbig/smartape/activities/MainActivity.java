package com.example.alexbig.smartape.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.ViewPagerAdapter;
import com.example.alexbig.smartape.fragments.QuizListFragment;
import com.example.alexbig.smartape.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private QuizListFragment quizListFragment;
    private ViewPagerAdapter viewPagerAdapter;
    private FloatingActionButton addFAB;

    public static List<Quiz> quizList = new ArrayList<>();
    public static List<Quiz> fullList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTabs();
        setDrawer();
    }

    private void setTabs() {
        fragmentManager = getSupportFragmentManager();
        quizListFragment = new QuizListFragment();

        addFAB = findViewById(R.id.addFAB);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.addFragment(quizListFragment, getString(R.string.tab_quizzes));
        tabLayout.setupWithViewPager(viewPager);

        Quiz quiz1 = new Quiz();
        quiz1.setTitle("Quiz 1");
        quiz1.setDescription("Quiz description");
        quiz1.setCreator("Test");
        fullList.add(quiz1);
        Quiz quiz2 = new Quiz();
        quiz2.setTitle("Quiz 2");
        quiz2.setDescription("Quiz description");
        quiz2.setCreator("Test");
        fullList.add(quiz2);
        Quiz quiz3 = new Quiz();
        quiz3.setTitle("Quiz 3");
        quiz3.setDescription("Quiz description");
        quiz3.setCreator("Test");
        fullList.add(quiz3);
        quizList.addAll(fullList);
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
        quizList.clear();
        quizList.addAll(fullList);
        viewPagerAdapter.notifyDataSetChanged();
    }

    private void openFavorites() {
        addFAB.setVisibility(View.INVISIBLE);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sortFavorites();
    }

    private void openSaved() {
        addFAB.setVisibility(View.INVISIBLE);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sortSaved();
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
                fullList.add(quiz);
            }
        });
        sortMyQuizzes();
    }

    private void sortSaved() {
        quizList.clear();
        for (Quiz q : fullList) {
            if (q.isSaved()) {
                quizList.add(q);
            }
        }
        viewPagerAdapter.notifyDataSetChanged();
    }

    private void sortFavorites() {
        quizList.clear();
        for (Quiz q : fullList) {
            if (q.isFavorite()) {
                quizList.add(q);
            }
        }
        viewPagerAdapter.notifyDataSetChanged();
    }

    private void sortMyQuizzes() {
        quizList.clear();
        for (Quiz q : fullList) {
            if (q.getCreator().equals("USER")) {
                quizList.add(q);
            }
        }
        viewPagerAdapter.notifyDataSetChanged();
    }
}
