package com.example.week_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ImageIntent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent myIntent = new Intent();
        myIntent.setType("image/pictures/*");
        myIntent.setAction(Intent.ACTION_GET_CONTENT);

        startActivity(myIntent);
    }
}