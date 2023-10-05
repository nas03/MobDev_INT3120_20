package com.example.week_9;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.UserDictionary;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }

    // defining authority so that other application can access it
    static final String PROVIDER_NAME = "com.demo.user.provider";

    // defining content URI
    static final String URL = "content://" + PROVIDER_NAME + "/users";

    // parsing the content URI
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String id = "id";
    static final String name = "name";
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    private static HashMap<String, String> values;

    static {

        // to match the content URI
        // every time user access table under content provider
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // to access whole table
        uriMatcher.addURI(PROVIDER_NAME, "users", uriCode);

        // to access a particular row
        // of the table
        uriMatcher.addURI(PROVIDER_NAME, "users/*", uriCode);
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/users";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    // creating the database
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Queries the UserDictionary and returns results
        Cursor cursor = getContext().getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,  // The content URI of the words table
                projection,                        // The columns to return for each row
                selection,                   // Selection criteria
                selectionArgs,                     // Selection criteria
                sortOrder);                        // The sort order for the returned rows
        return cursor;
    }


    // adding data to the database
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Defines a new Uri object that receives the result of the insertion
        Uri newUri;
// Defines an object to contain the new values to insert
        ContentValues newValues = new ContentValues();

        /*
         * Sets the values of each column and inserts the word. The arguments to the "put"
         * method are "column name" and "value".
         */
        newValues.put(UserDictionary.Words.APP_ID, "example.user");
        newValues.put(UserDictionary.Words.LOCALE, "en_US");
        newValues.put(UserDictionary.Words.WORD, "insert");
        newValues.put(UserDictionary.Words.FREQUENCY, "100");

        newUri = getContext().getContentResolver().insert(
                UserDictionary.Words.CONTENT_URI,   // The UserDictionary content URI
                newValues                           // The values to insert
        );
        return newUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Defines an object to contain the updated values
        ContentValues updateValues = new ContentValues();

// Defines selection criteria for the rows you want to update
        String selectionClause = UserDictionary.Words.LOCALE + " LIKE ?";
        selectionArgs = new String[]{"en_%"};

// Defines a variable to contain the number of updated rows
        int rowsUpdated = 0;
        /*
         * Sets the updated value and updates the selected words.
         */
        updateValues.putNull(UserDictionary.Words.LOCALE);

        rowsUpdated = getContext().getContentResolver().update(
                UserDictionary.Words.CONTENT_URI,  // The UserDictionary content URI
                updateValues,                      // The columns to update
                selectionClause,                   // The column to select on
                selectionArgs                      // The value to compare to
        );
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Defines selection criteria for the rows you want to delete
        String selectionClause = UserDictionary.Words.APP_ID + " LIKE ?";
         selectionArgs = new String[] {"user"};

// Defines a variable to contain the number of rows deleted
        int rowsDeleted = 0;

// Deletes the words that match the selection criteria
        rowsDeleted = getContext().getContentResolver().delete(
                UserDictionary.Words.CONTENT_URI,  // The UserDictionary content URI
                selectionClause,                   // The column to select on
                selectionArgs                      // The value to compare to
        );
        return rowsDeleted;
    }

    // creating object of database
    // to perform query
    private SQLiteDatabase db;

    // declaring name of the database
    static final String DATABASE_NAME = "UserDB";

    // declaring table name of the database
    static final String TABLE_NAME = "Users";

    // declaring version of the database
    static final int DATABASE_VERSION = 1;

    // sql query to create the table
    static final String CREATE_DB_TABLE = " CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT NOT NULL);";

    // creating a database
    private static class DatabaseHelper extends SQLiteOpenHelper {

        // defining a constructor
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // creating a table in the database
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // sql query to drop a table
            // having similar name
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
