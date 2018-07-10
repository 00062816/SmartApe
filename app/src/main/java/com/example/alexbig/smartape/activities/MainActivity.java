package com.example.alexbig.smartape.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
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

import com.abemart.wroup.common.WiFiDirectBroadcastReceiver;
import com.abemart.wroup.common.WiFiP2PInstance;
import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.adapters.ViewPagerAdapter;
import com.example.alexbig.smartape.api.APIRequest;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.fragments.QuizListFragment;
import com.example.alexbig.smartape.models.Quiz;
import com.example.alexbig.smartape.utils.ActivityManager;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        APIRequest apiRequest = new APIRequest(this);

        if (apiRequest.checkLogin()){
            setTabs();
            setDrawer();

        }else {
            Intent intentL = new Intent(this, LoginActivity.class);
            startActivity(intentL);
            finish();
        }
    }

    private void setTabs() {
        fragmentManager = getSupportFragmentManager();
        quizListFragment = new QuizListFragment();
        quizListFragment.setApiRequest(apiRequest);
<<<<<<< HEAD
=======

        addFAB = findViewById(R.id.addFAB);
>>>>>>> parent of ef98964... Correccion de errores y funcionalidad para editar y quitar preguntas y respuestas
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
                        quizListFragment.filterQuizzes(1);
                        break;

                    case R.id.menu_favorite_item:
                        quizListFragment.filterQuizzes(2);
                        break;

                    case R.id.menu_saved_item:
                        quizListFragment.filterQuizzes(3);
                        break;

                    case R.id.menu_myquizzes_item:
                        quizListFragment.filterQuizzes(4);
                        break;

                    case R.id.menu_broadcast_item:
                        openBroadcastActivity();
                        break;

                    case R.id.submenu_donate_item:
                        donateActivity();
                        break;

                    case R.id.submenu_logout:
                        logoutButtonClicked();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

<<<<<<< HEAD

=======
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
                Intent intent = new Intent(getApplicationContext(), CreateQuizActivity.class);
                startActivity(intent);
            }
        });
        quizListFragment.sortMyQuizzes();
    }
>>>>>>> parent of ef98964... Correccion de errores y funcionalidad para editar y quitar preguntas y respuestas

    public void openBroadcastActivity(){
        Intent intent = new Intent(this, BroadcastActivity.class);
        startActivity(intent);
    }

    public void donateActivity(){
        Intent intentD = new Intent(this, DonateActivity.class);
        startActivity(intentD);

    }
    public void logoutButtonClicked(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("logged", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        startActivity(new Intent(this, LoginActivity.class));

        finish();
    }

    private void refresh(){
        apiRequest.refresh();
    }
}
