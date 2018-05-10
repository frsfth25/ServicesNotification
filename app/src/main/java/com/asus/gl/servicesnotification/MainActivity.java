package com.asus.gl.servicesnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String TAG= "SERVICE";
    private EditText txtURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtURL =findViewById(R.id.txtURL);
    }


    public void startService(View view) {
        String URL = txtURL.getText().toString();
        Intent serviceIntent = new Intent(this, DownloaderService.class);
        serviceIntent.putExtra("URL", URL);
        Log.d(TAG,  "Starting the Downloader Service...");
        serviceIntent.setAction(DownloaderService.ACTION_DOWNLOAD);
        startService(serviceIntent);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloaderService.ACTION_DOWNLOAD);
        registerReceiver(new ServiceDownloaderReciever(), intentFilter);


    }

    private class ServiceDownloaderReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String info = intent.getStringExtra("data");
            Toast.makeText(MainActivity.this,"Receiver got a message:" + info,Toast.LENGTH_LONG).show();


        }
    }
}
