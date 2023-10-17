package com.example.week_10;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class SubActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private static final int RECORD_VIDEO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            Log.d("Sensor", "Success");
        } else {
            Log.d("Sensor", "Failed");
        }

        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
        Toast.makeText(this, activeNetwork.toString(), Toast.LENGTH_LONG);
        String service = Context.WIFI_SERVICE;

        WifiManager wifi = (WifiManager) getSystemService(service);
        WifiInfo wifiInfo = wifi.getConnectionInfo();
        Toast.makeText(this, wifiInfo.toString(), Toast.LENGTH_SHORT).show();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        List<WifiConfiguration> configurations = wifi.getConfiguredNetworks();
        for (WifiConfiguration config : configurations) {
            Log.d("Wifi", config.toString());
        }
        //Get the network ID for the first one
        if (configurations.size() > 0) {

            int netID = configurations.get(0).networkId;
            Toast.makeText(this, netID, Toast.LENGTH_SHORT).show();
            //Enable that network
            boolean disableAllOthers = true;
            wifi.disableNetwork(netID);
            wifi.enableNetwork(netID, disableAllOthers);
        }
//        //CALL INTENT
//        Intent whoYouGonnaCall = new Intent(Intent.ACTION_DIAL);
//        whoYouGonnaCall.setData(Uri.parse("tel:555--2368"));
//        startActivity(whoYouGonnaCall);
//        //Access telephonyAPIs using TelephonyManager
//        String srvcName = Context.TELECOM_SERVICE;
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(srvcName);
//        Toast.makeText(this, telephonyManager.getDataActivity(), Toast.LENGTH_SHORT).show();
//        //Start SMS intent
//        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:55512345"));
//        smsIntent.putExtra("sms_body", "Press send to send me");
//        startActivity(smsIntent);
//        Camera camera = Camera.open();
//        camera.release();
    }

    private void startRecording() {
// Generate the Intent.

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
// Launch the camera app.
        startActivityForResult(intent, RECORD_VIDEO);
    }
}