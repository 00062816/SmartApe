package com.example.alexbig.smartape.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.api.APIRequest;
import com.example.alexbig.smartape.api.SmartApeAPI;
import com.example.alexbig.smartape.api.deserializers.TokenDeserializer;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andres on 5/7/2018.
 */

public class LoginActivity extends AppCompatActivity {
    private Button boton;
    private EditText usuario, contraseña;
    private APIRequest apiRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        usuario = findViewById(R.id.editText_login_user);
        contraseña = findViewById(R.id.editText_login_password);
        boton = findViewById(R.id.button_login_logIn);

        QuizViewModel quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        apiRequest = new APIRequest(this, quizViewModel);

        boton.setOnClickListener(v -> Click());
    }

    public void Click() {
        if (usuario.getText().toString().equals("") || contraseña.getText().toString().equals("")) {
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();

        } else {
            apiRequest.login(usuario.getText().toString(), contraseña.getText().toString());
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}



