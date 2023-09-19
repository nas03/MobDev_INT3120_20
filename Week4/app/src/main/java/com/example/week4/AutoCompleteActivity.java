package com.example.week4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AutoCompleteActivity extends AppCompatActivity implements TextWatcher {
    TextView selection;
    String[] items = {"Android", "IPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_autocomplete);
        selection = findViewById(R.id.selection);
        AutoCompleteTextView edit = findViewById(R.id.edit);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        edit.setAdapter(adapter);
        edit.addTextChangedListener(this);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        selection.setText(selection.getText());
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void afterTextChanged(Editable s) {
    }

}
