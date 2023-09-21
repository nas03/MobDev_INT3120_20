package com.example.week_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class SendIntent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");

        //data
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello");

        //category
        sendIntent.addCategory(Intent.CATEGORY_DEFAULT);

        //component
        sendIntent.setComponent(new ComponentName("com.example.app", "com.example.app.SendMessageActivity"));

        startActivity(sendIntent);
    }
}