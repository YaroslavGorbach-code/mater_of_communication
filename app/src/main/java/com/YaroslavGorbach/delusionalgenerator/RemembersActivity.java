package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;

import java.util.Calendar;

public class RemembersActivity extends AppCompatActivity {

    private CheckBox mCheckM;
    private CheckBox mCheckT;
    private CheckBox mCheckW;
    private CheckBox mCheckTh;
    private CheckBox mCheckF;
    private CheckBox mCheckS;
    private CheckBox mCheckSt;

    private AlarmManager mAlarmManager;
    private Intent mReminderIntent;

    private PendingIntent mPendingIntentM;
    private PendingIntent mPendingIntentT;
    private PendingIntent mPendingIntentW;
    private PendingIntent mPendingIntentTh;
    private PendingIntent mPendingIntentF;
    private PendingIntent mPendingIntentS;
    private PendingIntent mPendingIntentSt;

    private Repo mRepo;


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

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mReminderIntent = new Intent(RemembersActivity.this, ReminderBroadcast.class);

        mPendingIntentM = PendingIntent.getBroadcast(RemembersActivity.this, 1, mReminderIntent, 0);
        mPendingIntentT = PendingIntent.getBroadcast(RemembersActivity.this, 2, mReminderIntent, 0);
        mPendingIntentW = PendingIntent.getBroadcast(RemembersActivity.this, 3, mReminderIntent, 0);
        mPendingIntentTh = PendingIntent.getBroadcast(RemembersActivity.this, 4, mReminderIntent, 0);
        mPendingIntentF = PendingIntent.getBroadcast(RemembersActivity.this, 5, mReminderIntent, 0);
        mPendingIntentS = PendingIntent.getBroadcast(RemembersActivity.this, 6, mReminderIntent, 0);
        mPendingIntentSt = PendingIntent.getBroadcast(RemembersActivity.this, 7, mReminderIntent, 0);

        mRepo = Repo.getInstance(this);

        mCheckM.setChecked(mRepo.getNotificationState(1));
        mCheckT.setChecked(mRepo.getNotificationState(2));
        mCheckW.setChecked(mRepo.getNotificationState(3));
        mCheckTh.setChecked(mRepo.getNotificationState(4));
        mCheckF.setChecked(mRepo.getNotificationState(5));
        mCheckSt.setChecked(mRepo.getNotificationState(6));
        mCheckS.setChecked(mRepo.getNotificationState(7));


        mCheckM.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);
                setAlarm(calendar, mPendingIntentM);
               mRepo.changeNotificationState(1,1);

            }else {
                mAlarmManager.cancel(mPendingIntentM);
                mRepo.changeNotificationState(1,0);

            }
        });

        mCheckT.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);
                setAlarm(calendar, mPendingIntentT);
                mRepo.changeNotificationState(2,1);

            }else {
                mAlarmManager.cancel(mPendingIntentT);
                mRepo.changeNotificationState(2,0);
            }
        });

        mCheckW.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 5);
                setAlarm(calendar, mPendingIntentW);
                mRepo.changeNotificationState(3,1);

            }else {
                mAlarmManager.cancel(mPendingIntentW);
                mRepo.changeNotificationState(3,0);
            }
        });

        mCheckTh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 10);
                setAlarm(calendar, mPendingIntentTh);
                mRepo.changeNotificationState(4,1);

            }else{
                mAlarmManager.cancel(mPendingIntentTh);
                mRepo.changeNotificationState(4,0);
            }
        });

        mCheckF.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.FEBRUARY);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);
                setAlarm(calendar, mPendingIntentF);
                mRepo.changeNotificationState(5,1);
            }else {
                mAlarmManager.cancel(mPendingIntentF);
                mRepo.changeNotificationState(5,0);
            }
        });

        mCheckSt.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);
                setAlarm(calendar, mPendingIntentS);
                mRepo.changeNotificationState(6,1);
            }else {
                mAlarmManager.cancel(mPendingIntentS);
                mRepo.changeNotificationState(6,0);
            }
        });

        mCheckS.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 50);
                setAlarm(calendar, mPendingIntentSt);
                mRepo.changeNotificationState(7,1);
            }else {
                mAlarmManager.cancel(mPendingIntentSt);
                mRepo.changeNotificationState(7,0);
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
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setAlarm(Calendar calendar, PendingIntent pendingIntent){
        assert mAlarmManager != null;
        mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY * 6, pendingIntent);
    }
}