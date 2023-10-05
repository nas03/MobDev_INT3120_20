package com.example.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GreetingActivity extends AppCompatActivity {
    AirplaneModeChangeReceiver receiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.text_greeting);
        textView.setText(intent.getStringExtra("message"));
    }
}

