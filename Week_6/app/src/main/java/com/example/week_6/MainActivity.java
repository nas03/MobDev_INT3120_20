package com.example.week_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 2;
    int reqCode = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.item2);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item2) {
            {
                setContentView(R.layout.activity_donate_constraint);
            }
//                Intent intent = new Intent(this, DonateActivity.class);
//                startActivityForResult(intent, MY_REQUEST_CODE);
//            }
        } else if (id == R.id.item3) {
            setContentView(R.layout.activity_essentials_constraint);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
//            String feedback = data.getStringExtra("feedback");
//            textFeedback.setText(feedback);
//        } else {
//            textFeedback.setText("!?");
//        }
    }
}