package com.example.notificationappmsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RemainderBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context, "Notification")
                .setSmallIcon(R.drawable.add_equipment)
                .setContentTitle("Remind me")
                .setContentText("Hey this is soft remainder....")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);

        notificationManager.notify(300, builder.build());
    }
}
