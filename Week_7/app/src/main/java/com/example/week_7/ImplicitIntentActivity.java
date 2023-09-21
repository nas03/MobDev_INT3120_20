package com.example.week_7;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;


public class ImplicitIntentActivity extends Activity {
    private Button buttonBackToMain = null;
    private Button buttonIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        this.buttonBackToMain = (Button) this.findViewById(R.id.button_back);
        this.buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImplicitIntentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_intent_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.search) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "How to A+ mobile");
            startActivity(intent);

            return true;
        }

        if (id == R.id.phone) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:068686868"));
            startActivity(intent);
            return true;
        }

        if (id == R.id.sms) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:0888999666"));
            intent.putExtra("sms body", "are we going to UET?");
            startActivity(intent);
            return true;
        }

        if (id == R.id.picture) {
            Intent intent = new Intent();
            intent.setType("image/pictures/");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivity(intent);

            return true;
        }

        if (id == R.id.contact) {
            String myData = "content://contacts/people/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));
            startActivity(intent);
            return true;
        }

        if (id == R.id.geo) {
            String geoCode = "geo:0,0?q=1860+east+18th+street+cleveland+oh";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
            startActivity(intent);
            return true;
        }

        if (id == R.id.music) {
            Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}


