package com.YaroslavGorbach.delusionalgenerator.Helpers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.YaroslavGorbach.delusionalgenerator.Activities.MainActivity;
import com.YaroslavGorbach.delusionalgenerator.R;

import static com.YaroslavGorbach.delusionalgenerator.R.drawable.ic_notifi;

public class ReminderBroadcast extends BroadcastReceiver {

    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final int NOTIFICATION_ID_1 = 1;
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Речь сам себя не улучшит!")
                .setContentText("Пора чуть-чуть поговорить.")
                .setSmallIcon(ic_notifi)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID_1, builder.build());

    }
}
