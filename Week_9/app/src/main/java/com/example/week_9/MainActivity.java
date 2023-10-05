package com.example.week_9;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends Activity {
    Cursor mCursor;
    String searchString;
    EditText searchWord;
    ListView wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Defines a list of columns to retrieve from the Cursor and load into an output row
        String[] wordListColumns =
                {
                        UserDictionary.Words.WORD,   // Contract class constant containing the word column name
                        UserDictionary.Words.LOCALE  // Contract class constant containing the locale column name
                };

// Defines a list of View IDs that receive the Cursor columns for each row
        int[] wordListItems = {R.id.dictWord, R.id.locale};

// Creates a new SimpleCursorAdapter
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),               // The application's Context object
                R.layout.wordlistrow,                  // A layout in XML for one row in the ListView
                mCursor,                               // The result from the query
                wordListColumns,                       // A string array of column names in the cursor
                wordListItems,                         // An integer array of view IDs in the row layout
                0);                                    // Flags (usually none are needed)
    wordList = findViewById(R.id.wordList);
// Sets the adapter for the ListView
        wordList.setAdapter(cursorAdapter);
    }
}
