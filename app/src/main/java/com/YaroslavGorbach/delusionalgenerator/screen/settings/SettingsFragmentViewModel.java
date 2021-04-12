package com.YaroslavGorbach.delusionalgenerator.screen.settings;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.YaroslavGorbach.delusionalgenerator.data.oldDataLayer.Repo_SQLite;
import com.YaroslavGorbach.delusionalgenerator.util.ReminderBroadcast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class SettingsFragmentViewModel extends AndroidViewModel{
    private final Repo_SQLite mRepoSQLite;
    private final AlarmManager mAlarmManager;
    private final PendingIntent mPendingIntent;
    private static final String CHANNEL_ID = "CHANNEL_ID";


    public SettingsFragmentViewModel(@NonNull Application application){
        super(application);

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = application.getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        mRepoSQLite = Repo_SQLite.getInstance(application.getApplicationContext());
        mAlarmManager = (AlarmManager) application.getApplicationContext()
                .getSystemService(ALARM_SERVICE);
        Intent mReminderIntent = new Intent(application.getApplicationContext(), ReminderBroadcast.class);
        mPendingIntent = PendingIntent.getBroadcast(application.getApplicationContext(),
                1, mReminderIntent, 0);

    }

    private void setAlarm(Calendar calendar, PendingIntent pendingIntent) {
        assert mAlarmManager != null;
        mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY , pendingIntent);
    }

    public void setNotification(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepoSQLite.getNotifyHour()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(mRepoSQLite.getNotifyMinute()));
        setAlarm(calendar, mPendingIntent);
        mRepoSQLite.changeNotificationState(1);
    }
    public void cancelNotification(){
        mAlarmManager.cancel(mPendingIntent);
        mRepoSQLite.changeNotificationState(0);
    }


}
