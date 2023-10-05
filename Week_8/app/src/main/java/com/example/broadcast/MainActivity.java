package com.example.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    public static final String ACTION_GREETING_EVENT = "com.example.greetingevent";
    public static final String EXTRA_MESSAGE = "message";
    private static final String PREFS_NAME = "Preferences";
    private boolean mSilentMode = false;

    IntentFilter intentFilter;
    FeedReaderDbHelper mDbHelper;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new FeedReaderDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        try {
            registerReceiver(airplaneModeChangeReceiver, filter, RECEIVER_EXPORTED);
            Log.d("Register", "Success");
        } catch (Error e) {
            Log.d("Register", "Failed");
        }
//        boolean isRegistered = airplaneModeChangeReceiver.isOrderedBroadcast();
//        if(isRegistered)
//            Toast.makeText(this, "broadcast is registered", Toast.LENGTH_LONG);
//        else
//            Toast.makeText(this, "broadcast is not registered", Toast.LENGTH_LONG);
    }

    @Override
    public void onResume() {
        super.onResume();
//      registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED);
    }


    public void sendGreetingEvent(View view) {
        Intent intent = new Intent("com.example.ACTION_MY_EVENT");
        intent.putExtra("message", "Hello from Main");
        sendBroadcast(intent);
    }

    public void performDatabaseOperation(View view) {
        //        PERFORM DATABASE QUERY
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        String[] projection = {FeedReaderContract.FeedEntry._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE};
//        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = {"My Title"};
//
//        Cursor cursor = db.query(
//                FeedReaderContract.FeedEntry.TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        )


        //       INSERT
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        String title = "titleMap";
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
//        String subtitle = "subtitleMap";
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
//
//
//        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);


        //  DELETE
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";

        String[] selectionArgs = {"MyTitle"};

        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);

    }

    public void changePreference() {
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", mSilentMode);
        // Commit the edits!
        editor.commit();
    }

    public void readFileInputStream() throws IOException {
        try {
            FileInputStream fis = openFileInput("hello.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            fis.close();

            // Process the data read from the file
            String fileContent = sb.toString();
            // Do something with fileContent
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public File getAlbumStorageDir(String albumName) {
//        // Get the directory for the user's public pictures directory.
//        File file = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), albumName);
//        if (!file.mkdirs()) {
//            Log.e(LOG_TAG, "Directory not created");
//        }
//        return file;
//    }

    void deleteExternalStoragePrivateFile() {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(getExternalFilesDir(null), "DemoFile.jpg");
        if (file != null) {
            file.delete();
        }
    }


}