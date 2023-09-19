package com.example.week4;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView selection;
    String[] items = {"Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        selection = findViewById(R.id.selection);
        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        // Get the root layout of your activity using the myLinearLayout ID
        LinearLayout rootLayout = findViewById(R.id.myLinearLayout);

        // Add an OnTouchListener to the root layout
        rootLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Check if the touch event is outside the Spinner
                if (event.getAction() == MotionEvent.ACTION_DOWN && !isPointInsideView(event.getRawX(), event.getRawY(), spin)) {
                    selection.setText("");
                }
                return false;
            }
        });
    }

    // Helper method to check if a point is inside a view
    private boolean isPointInsideView(float x, float y, View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];
        return (x > viewX && x < (viewX + view.getWidth()) && y > viewY && y < (viewY + view.getHeight()));
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        if (position == -1) selection.setText("");
        else selection.setText(items[position]);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
    }
}
