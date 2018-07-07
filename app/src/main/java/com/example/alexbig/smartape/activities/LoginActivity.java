package com.example.alexbig.smartape.activities;

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
import com.example.alexbig.smartape.api.SmartApeAPI;
import com.example.alexbig.smartape.api.deserializers.TokenDeserializer;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        usuario = findViewById(R.id.editText_login_user);
        contraseña = findViewById(R.id.editText_login_password);
        boton = findViewById(R.id.button_login_logIn);

        boton.setOnClickListener(v -> Click());
    }

    public void Click() {
        if (usuario.getText().toString().equals("") || contraseña.getText().toString().equals("")) {
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();

        } else {

            Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create();
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(SmartApeAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            SmartApeAPI noticiasApi = retrofit.create(SmartApeAPI.class);

            Call<String> stringCall = noticiasApi.login(usuario.getText().toString(), contraseña.getText().toString());
            stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    System.out.println(response.code());
                    if (response.code() == 200) {
                        Toast.makeText(LoginActivity.this, "Sesión iniciada con exito", Toast.LENGTH_SHORT).show();
                        TokenGuardado(response.body());
                        startMainActivity();
                    } else if (response.code() == 404) {

                        Toast.makeText(LoginActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                    } else if(response.code() == 500){

                        Toast.makeText(LoginActivity.this, "there was a problem finding the user", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(LoginActivity.this, "Error: check later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        Toast.makeText(LoginActivity.this, "Time out", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void TokenGuardado(String token) {
        SharedPreferences preferences = this.getSharedPreferences("logged", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }


    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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



