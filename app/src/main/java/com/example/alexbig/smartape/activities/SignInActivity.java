package com.example.alexbig.smartape.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.api.APIRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, rePasswordEditText;
    private APIRequest apiRequest;
    private Button button;

    private ProgressBar progressBarSignIn;
    private RelativeLayout mainContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_in);

        apiRequest = new APIRequest(this);
        emailEditText = findViewById(R.id.editText_signIn_email);
        passwordEditText = findViewById(R.id.editText_signIn_password);
        rePasswordEditText = findViewById(R.id.editText_signIn_rePassword);

        mainContainer = findViewById(R.id.relativeLayout_signIn_allFields);
        progressBarSignIn = findViewById(R.id.progressBar_signIn_loading);

        button = findViewById(R.id.button_signIn_sign);
        button.setOnClickListener(v -> clickedButton());
    }

    private void clickedButton() {
        if (noEmptyFields(emailEditText.getText().toString(), passwordEditText.getText().toString(), rePasswordEditText.getText().toString())){
            if (isEmailValid(emailEditText.getText().toString())){
                if (passwordMatches(passwordEditText.getText().toString(), rePasswordEditText.getText().toString())){
                    boolean createdSuccesfully;
                    mainContainer.setVisibility(View.GONE);
                    progressBarSignIn.setVisibility(View.VISIBLE);
                    apiRequest.signIn(emailEditText.getText().toString(), passwordEditText.getText().toString(), progressBarSignIn, mainContainer);
                }else {
                    Toast.makeText(this, "Password don't match", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Entry is not an email", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Fields can't go empty", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailValid(String email){
        //String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean passwordMatches(String password1, String password2){
        boolean matches = false;
        if (password1.equals(password2)){
            matches = true;
        }else {
            matches = false;
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        }
        return matches;
    }

    private boolean noEmptyFields(String email, String password, String repassword){
        boolean flag;
        if (emailEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("") || rePasswordEditText.getText().toString().equals("")){
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }

}
