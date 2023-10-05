package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class AirplaneModeChangeReceiver extends BroadcastReceiver {
    //    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.d("API123", "" + intent.getAction());
//        String message = intent.getStringExtra("message");
//        if (Objects.equals(intent.getAction(), "com.example.ACTION_MY_EVENT")) {
//            //Message
////            Intent newIntent = new Intent(context, GreetingActivity.class);
////            newIntent.putExtra("message", message);
////            context.startActivity(newIntent);
//            //view Google
////            Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
////            context.startActivity(newIntent);
//            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
//
//        } else {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//            boolean isConnected = activeNetwork != null &&
//                    activeNetwork.isConnectedOrConnecting();
//            if (isConnected) {
//                try {
//                    Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isOrdered = isOrderedBroadcast();
        if (isOrdered) {
            Log.d(" registered", "True");
            if (isAirplaneModeOn(context.getApplicationContext())) {
                Toast.makeText(context, "AirPlane mode is on", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "AirPlane mode is off", Toast.LENGTH_SHORT).show();

            }
        } else {
            Log.d(" registered", "False");
        }
    }

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}
