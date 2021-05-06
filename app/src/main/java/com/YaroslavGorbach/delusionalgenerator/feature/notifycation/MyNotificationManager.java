package com.YaroslavGorbach.delusionalgenerator.feature.notifycation;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

public interface MyNotificationManager {
    void sendNotification(NotificationManager notificationManager, Context context, String messageBody);
    void sendNotificationOnTime(NotificationManager notificationManager, Context context, String messageBody, long time);

}
