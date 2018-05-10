package com.asus.gl.servicesnotification;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


//public class DownloaderService extends Service {
public class DownloaderService extends IntentService {
    public static String CHANNEL_ID= "1234";
    public static String ACTION_TOAST ="toastMessage";
    public static String ACTION_DOWNLOAD = "downloadAction";

    public DownloaderService(){
        super("this");
    }
    public DownloaderService(String name) {
        super(name);
    }


    /*public DownloaderService() {

        Log.d(MainActivity.TAG, "Service const. called");
    }*/

    @Override
    //public int onStartCommand(Intent intent, int flag, int id){
    protected void onHandleIntent(Intent intent){
        Log.d(MainActivity.TAG, "Service started" );


        String downloadURL = intent.getStringExtra("URL");
        Log.d(MainActivity.TAG, "Downloading file: " + downloadURL);
        if(intent.getAction().equals(ACTION_TOAST)){
            try {
                Thread.sleep(1000);
                Toast.makeText(this, "This is Toast Action", Toast.LENGTH_SHORT).show();

                Intent resultingIntent = new Intent();
                resultingIntent.setAction(ACTION_TOAST);
                resultingIntent.putExtra("data", "ToastService runned");
                sendBroadcast(resultingIntent);

            }catch (InterruptedException e){
                Log.d(MainActivity.TAG, "Thread is interrupted while downloading " + downloadURL);
            }
        }
        else if(intent.getAction().equals(ACTION_DOWNLOAD)){
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                Log.d(MainActivity.TAG, "Thread is interrupted while downloading " + downloadURL);
            }

            Intent resultingIntent = new Intent();
            resultingIntent.setAction(ACTION_DOWNLOAD);
            resultingIntent.putExtra("data", "Download Service finished.");
            sendBroadcast(resultingIntent);
        }

        //Works API <=25, After API 25 Notification Builders changed totally
        if(Build.VERSION.SDK_INT <= 25) {
            Notification.Builder builder = new Notification.Builder(getBaseContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Downloader")
                    .setContentText("Download is finished");

            NotificationManager nm = (NotificationManager) getBaseContext().getSystemService(getBaseContext().NOTIFICATION_SERVICE);

            nm.notify(4343, builder.build());
        }
        else{
            Toast.makeText(this, "Works API <=25. After API 25 Notification Builders changed totally.", Toast.LENGTH_LONG).show();
        }



        //return START_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
