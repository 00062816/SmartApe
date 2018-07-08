package com.example.alexbig.smartape.utils;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    public static void makeToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
