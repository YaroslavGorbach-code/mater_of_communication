package com.YaroslavGorbach.delusionalgenerator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

//Клас создан для показа уведослений при выключеном приложении и в заданое время
public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, "test");
        Intent ii = new Intent(context, Main2Activity.class);
        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, ii, PendingIntent.FLAG_UPDATE_CURRENT);



        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Бредогенератор");
        mBuilder.setContentText("Не забывайте роботать над своей речью");
        mBuilder.setPriority(Notification.PRIORITY_MAX);

        NotificationManagerCompat mNotificationManagerCompat = NotificationManagerCompat.from(context);
        mNotificationManagerCompat.notify(1, mBuilder.build());
    }
}
