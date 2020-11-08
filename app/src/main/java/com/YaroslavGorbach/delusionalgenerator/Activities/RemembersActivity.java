package com.YaroslavGorbach.delusionalgenerator.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.ReminderBroadcast;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Fragments.TimePickerFragment;

import java.util.Calendar;

public class RemembersActivity extends AppCompatActivity {

    private CheckBox mCheckM;
    private CheckBox mCheckT;
    private CheckBox mCheckW;
    private CheckBox mCheckTh;
    private CheckBox mCheckF;
    private CheckBox mCheckS;
    private CheckBox mCheckSt;

    private TextView mTimePikerM;
    private TextView mTimePikerT;
    private TextView mTimePikerW;
    private TextView mTimePikerTh;
    private TextView mTimePikerF;
    private TextView mTimePikerS;
    private TextView mTimePikerSt;

    private AlarmManager mAlarmManager;
    private Intent mReminderIntent;

    private PendingIntent mPendingIntentM;
    private PendingIntent mPendingIntentT;
    private PendingIntent mPendingIntentW;
    private PendingIntent mPendingIntentTh;
    private PendingIntent mPendingIntentF;
    private PendingIntent mPendingIntentS;
    private PendingIntent mPendingIntentSt;

    private Toolbar mToolbar;

    private Repo mRepo;


    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final int NOTIFICATION_ID_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remembers);
        createNotificationChannel();

        mToolbar = findViewById(R.id.toolbar_remember);

        mCheckM = findViewById(R.id.appCompatCheckBoxM);
        mCheckT = findViewById(R.id.appCompatCheckBoxT);
        mCheckW = findViewById(R.id.appCompatCheckBoxW);
        mCheckTh = findViewById(R.id.appCompatCheckBoxTh);
        mCheckF = findViewById(R.id.appCompatCheckBoxF);
        mCheckS = findViewById(R.id.appCompatCheckBoxSn);
        mCheckSt = findViewById(R.id.appCompatCheckBoxSt);

        mTimePikerM = findViewById(R.id.timePikerM);
        mTimePikerT = findViewById(R.id.timePikerT);
        mTimePikerW = findViewById(R.id.timePikerW);
        mTimePikerTh = findViewById(R.id.timePikerTh);
        mTimePikerF = findViewById(R.id.timePikerF);
        mTimePikerSt = findViewById(R.id.timePikerSt);
        mTimePikerS = findViewById(R.id.timePikerSn);


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

        setNotifyTime();
        mRepo.addListener(this::setNotifyTime);

        mToolbar.setNavigationOnClickListener(v->{
            finish();
        });

        mTimePikerM.setOnClickListener(view -> {
            TimePickerFragment.newInstance(1)
                    .show(getSupportFragmentManager(), "timePicker");
            mCheckM.setChecked(false);
        });
        mTimePikerT.setOnClickListener(view -> {
            TimePickerFragment.newInstance(2)
                    .show(getSupportFragmentManager(), "timePicker");
            mCheckT.setChecked(false);

        });
        mTimePikerW.setOnClickListener(view -> {
            TimePickerFragment.newInstance(3)
                    .show(getSupportFragmentManager(), "timePicker");
            mCheckW.setChecked(false);
        });
        mTimePikerTh.setOnClickListener(view -> {
            TimePickerFragment.newInstance(4)
                    .show(getSupportFragmentManager(), "timePicker");
            mCheckTh.setChecked(false);
        });
        mTimePikerF.setOnClickListener(view -> {
            TimePickerFragment.newInstance(5)
                    .show(getSupportFragmentManager(), "timePicker");
            mCheckF.setChecked(false);
        });
        mTimePikerSt.setOnClickListener(view -> {
            TimePickerFragment.newInstance(6)
                    .show(getSupportFragmentManager(), "timePicker");
            mCheckSt.setChecked(false);
        });
        mTimePikerS.setOnClickListener(view -> {
            TimePickerFragment.newInstance(7)
                    .show(getSupportFragmentManager(), "timePicker");
            mCheckS.setChecked(false);
        });

        mCheckM.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHourByDayId(1)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinuteByDayId(1)));
                setAlarm(calendar, mPendingIntentM);
                mRepo.changeNotificationState(1, 1);

            } else {
                mAlarmManager.cancel(mPendingIntentM);
                mRepo.changeNotificationState(1, 0);
            }
        });

        mCheckT.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHourByDayId(2)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinuteByDayId(2)));
                setAlarm(calendar, mPendingIntentT);
                mRepo.changeNotificationState(2, 1);

            } else {
                mAlarmManager.cancel(mPendingIntentT);
                mRepo.changeNotificationState(2, 0);
            }
        });

        mCheckW.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHourByDayId(3)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinuteByDayId(3)));
                setAlarm(calendar, mPendingIntentW);
                mRepo.changeNotificationState(3, 1);

            } else {
                mAlarmManager.cancel(mPendingIntentW);
                mRepo.changeNotificationState(3, 0);
            }
        });

        mCheckTh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHourByDayId(4)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinuteByDayId(4)));
                setAlarm(calendar, mPendingIntentTh);
                mRepo.changeNotificationState(4, 1);

            } else {
                mAlarmManager.cancel(mPendingIntentTh);
                mRepo.changeNotificationState(4, 0);
            }
        });

        mCheckF.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHourByDayId(5)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinuteByDayId(5)));
                setAlarm(calendar, mPendingIntentF);
                mRepo.changeNotificationState(5, 1);
            } else {
                mAlarmManager.cancel(mPendingIntentF);
                mRepo.changeNotificationState(5, 0);
            }
        });

        mCheckSt.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHourByDayId(6)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinuteByDayId(6)));
                setAlarm(calendar, mPendingIntentS);
                mRepo.changeNotificationState(6, 1);
            } else {
                mAlarmManager.cancel(mPendingIntentS);
                mRepo.changeNotificationState(6, 0);
            }
        });

        mCheckS.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHourByDayId(7)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinuteByDayId(7)));
                setAlarm(calendar, mPendingIntentSt);
                mRepo.changeNotificationState(7, 1);
            } else {
                mAlarmManager.cancel(mPendingIntentSt);
                mRepo.changeNotificationState(7, 0);
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void setNotifyTime() {
        mTimePikerM.setText(mRepo.getNotifyHourByDayId(1) + ":" + mRepo.getNotifyMinuteByDayId(1));
        mTimePikerT.setText(mRepo.getNotifyHourByDayId(2) + ":" + mRepo.getNotifyMinuteByDayId(2));
        mTimePikerW.setText(mRepo.getNotifyHourByDayId(3) + ":" + mRepo.getNotifyMinuteByDayId(3));
        mTimePikerTh.setText(mRepo.getNotifyHourByDayId(4) + ":" + mRepo.getNotifyMinuteByDayId(4));
        mTimePikerF.setText(mRepo.getNotifyHourByDayId(5) + ":" + mRepo.getNotifyMinuteByDayId(5));
        mTimePikerSt.setText(mRepo.getNotifyHourByDayId(6) + ":" + mRepo.getNotifyMinuteByDayId(6));
        mTimePikerS.setText(mRepo.getNotifyHourByDayId(7) + ":" + mRepo.getNotifyMinuteByDayId(7));
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

    private void setAlarm(Calendar calendar, PendingIntent pendingIntent) {
        assert mAlarmManager != null;
        mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY * 6, pendingIntent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRepo.removeListener(this::setNotifyTime);
    }

    /*Установка темы*/
    private void setTheme(){
        String color = Repo.getInstance(RemembersActivity.this).getThemeState();
        switch (color){

            case "blue":
                setTheme(R.style.AppTheme_blue);
                break;

            case "green":
                setTheme(R.style.AppTheme_green);
                break;

            case "orange":
                setTheme(R.style.AppTheme_orange);
                break;

            case "red":
                setTheme(R.style.AppTheme_red);
                break;

            case "purple":
                setTheme(R.style.AppTheme_purple);
                break;

        }
    }
}