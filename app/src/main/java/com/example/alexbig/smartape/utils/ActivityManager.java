package com.example.alexbig.smartape.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.alexbig.smartape.activities.MainActivity;

public class ActivityManager {

    public static void openMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void closeActivity(Context context){
        ((AppCompatActivity)context).finish();
    }
}
