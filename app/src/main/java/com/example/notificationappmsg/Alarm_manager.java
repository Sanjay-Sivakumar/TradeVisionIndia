package com.example.notificationappmsg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Alarm_manager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        createNotificationChannel();
        Button button = findViewById(R.id.alarmbutton);

        button.setOnClickListener(v -> {
            Toast.makeText(this,"Remainder set!",Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(Alarm_manager.this,RemainderBroadcast.class);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(Alarm_manager.this,0,intent,0);

            AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

            long timeAtButtonClick =System.currentTimeMillis();
            long tenSecondsInMillis = 1000*10;
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick+tenSecondsInMillis,
                    pendingIntent);
        });
    }

    private void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "RemainderChannel";
            String description = "Channel for Remainder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("Notification",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}