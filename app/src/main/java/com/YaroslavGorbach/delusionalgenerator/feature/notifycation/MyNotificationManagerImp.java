package com.YaroslavGorbach.delusionalgenerator.feature.notifycation;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationCompat;
import com.YaroslavGorbach.delusionalgenerator.MainActivity;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.receivers.AlarmReceiver;

import static com.YaroslavGorbach.delusionalgenerator.receivers.AlarmReceiver.EXTRA_ALLOW;
import static com.YaroslavGorbach.delusionalgenerator.receivers.AlarmReceiver.EXTRA_MESSAGE;

public class MyNotificationManagerImp implements MyNotificationManager {
    @Override
    public void sendNotification(NotificationManager notificationManager, Context context, String messageBody) {
        Intent contentIntent = new Intent(context, MainActivity.class);
        int notificationId = 0;
        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                context,
                notificationId,
                contentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context,
                context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_notifi)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(messageBody)
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManager.notify(notificationId, builder.build());
    }

    @Override
    public void sendNotificationOnTime(NotificationManager notificationManager, Context context, long time, String messageBody) {
        Intent notifyIntent = new Intent(context, AlarmReceiver.class);
        notifyIntent.putExtra(EXTRA_MESSAGE, messageBody);
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, notifyPendingIntent);

    }
}
