package com.example.week_7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 2;
    Button buttonSendMessage;
    TextView editTextFullName;
    TextView textFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSendMessage = findViewById(R.id.button_sendMessage);
        editTextFullName = findViewById(R.id.editText_fullName);
        textFeedback = findViewById(R.id.textView_feedback);

        buttonSendMessage.setOnClickListener(v -> sendMessage());
    }

    public void sendMessage() {
        String fullName = editTextFullName.getText().toString();
        String message = "Hello, Please say hello to me!";
        Intent intent = new Intent(this, GreetingActivity.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("message", message);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            String feedback = data.getStringExtra("feedback");
            textFeedback.setText(feedback);
        } else {
            textFeedback.setText("!?");
        }
    }
}
