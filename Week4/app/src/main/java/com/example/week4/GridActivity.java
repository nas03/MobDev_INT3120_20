package com.example.week4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GridActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView selection;
    String[] items = {"Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        GridView gridView = (GridView) findViewById(R.id.grid);
        selection = findViewById(R.id.textView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        selection.setText(items[position]);
    }
}
