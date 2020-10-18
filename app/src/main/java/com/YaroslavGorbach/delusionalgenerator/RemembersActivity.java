package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.Calendar;
import java.util.Date;

import static androidx.work.ExistingWorkPolicy.REPLACE;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class RemembersActivity extends AppCompatActivity {

    private CheckBox mCheckM;
    private CheckBox mCheckT;
    private CheckBox mCheckW;
    private CheckBox mCheckTh;
    private CheckBox mCheckF;
    private CheckBox mCheckS;
    private CheckBox mCheckSt;

    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final int NOTIFICATION_ID_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remembers);
        createNotificationChannel();

        mCheckM = findViewById(R.id.appCompatCheckBoxM);
        mCheckT = findViewById(R.id.appCompatCheckBoxT);
        mCheckW = findViewById(R.id.appCompatCheckBoxW);
        mCheckTh = findViewById(R.id.appCompatCheckBoxTh);
        mCheckF = findViewById(R.id.appCompatCheckBoxF);
        mCheckS = findViewById(R.id.appCompatCheckBoxSn);
        mCheckSt = findViewById(R.id.appCompatCheckBoxSt);

        mCheckM.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);

                setAlarm(calendar);
            }
        });

        mCheckT.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 2);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);

                setAlarm(calendar);
            }
        });

        mCheckW.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 3);
                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 5);

                setAlarm(calendar);
            }
        });

        mCheckTh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 4);
                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 10);

                setAlarm(calendar);
            }
        });

        mCheckF.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 5);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);

                setAlarm(calendar);
            }
        });

        mCheckSt.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 6);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);

                setAlarm(calendar);
            }
        });

        mCheckS.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, 7);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);

                setAlarm(calendar);
            }
        });

    }

    private void createNotificationChannel() {
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
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setAlarm(Calendar calendar){

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(RemembersActivity.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                RemembersActivity.this, 0, intent, 0);

        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}