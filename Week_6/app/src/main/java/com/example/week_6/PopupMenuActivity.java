package com.example.week_6;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class PopupMenuActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Hello";
    Button button1;
    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_pop_up);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1Clicked();
            }
        });
    }

    private void button1Clicked() {
        PopupMenu popup = new PopupMenu(this, this.button2);
        popup.inflate(R.menu.layout_popup_menu);
        Menu menu = popup.getMenu();
        Log.i(LOG_TAG, "Menu class: " + menu.getClass().getName());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onMenuItemClick(menuItem);
            }
        });
        popup.show();
    }
}
