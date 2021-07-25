package com.example.notificationappmsg;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {

        Timer timer;
        TimerTask timerTask;
        String TAG = "Timers";
        int Your_X_SECS = 5;


        @Override
        public IBinder onBind(Intent arg0) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.e(TAG, "onStartCommand");
            super.onStartCommand(intent, flags, startId);

            startTimer();

            return START_STICKY;
        }


        @Override
        public void onCreate() {
            Log.e(TAG, "onCreate");


        }

        @Override
        public void onDestroy() {
            Log.e(TAG, "onDestroy");
            startTimer();
            super.onDestroy();


        }

        //we are going to use a handler to be able to run in our TimerTask
        final Handler handler = new Handler();


        public void startTimer() {
            //set a new Timer
            timer = new Timer();

            //initialize the TimerTask's job
            initializeTimerTask();

            //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
            timer.schedule(timerTask, 5000, Your_X_SECS * 1000); //
            //timer.schedule(timerTask, 5000,1000); //
        }

        public void stoptimertask() {
            //stop the timer, if it's not already null
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }

        public void initializeTimerTask() {

            timerTask = new TimerTask() {
                public void run() {

                    //use a handler to run a toast that shows the current timestamp
                    handler.post(new Runnable() {
                        public void run() {

                            notification("1","sanjay","100");
                            //TODO CALL NOTIFICATION FUNC



                        }
                    });
                }
            };
        }

    public void notification(String nBedNo, String nname, String nIVreading){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

       // if (!nIVreading.isEmpty()){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                    .setContentText("Code Sphere")
                    .setSmallIcon(R.drawable.add_equipment)
                    .setAutoCancel(true)
                    .setContentText(nIVreading + " SANJAY " + nBedNo);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(999, builder.build());

        //}
    }
}


