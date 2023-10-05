package com.example.week_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DonateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_constraint);
        Intent intent = this.getIntent();
    }

    public void goBack() {
        this.onBackPressed();
    }

    public void finish() {
        Intent data = new Intent();
//        String feedback = "OK, Hello " + fullName + ". How are you?";
//        data.putExtra("feedback", feedback);

        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

}
