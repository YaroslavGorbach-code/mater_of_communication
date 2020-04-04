package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    int mIdEx;
    private TextView mExName;
    private TextView mAimOfExercise;
    private TextView mHawToPerformTheExercise;
    public static final String EXTRA_ID = "EXTRA_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initializeComponents();

        mToolbar.setNavigationOnClickListener(v->{

            finish();
        });

        switch (mIdEx){
            case 1:
                mToolbar.setTitle("Лингвистические пирамиды");
                mExName.setText("Лингвистические пирамиды");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_1));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_1));
                break;
            case 2:
                mToolbar.setTitle("Чем ворон похож на стол");
                break;
            case 3:
                mToolbar.setTitle("Чем ворон похож на стол 2");
                break;
            case 4:
                mToolbar.setTitle("Продвинутое связывание");
                break;
            case 5:
                mToolbar.setTitle("О чем вижу, о том и пою");
                break;
            case 6:
                mToolbar.setTitle("Другие варианты сокращений");
                break;
        }

    }

    private void initializeComponents(){
        mToolbar = findViewById(R.id.toolbar_notification);
        mIdEx = getIntent().getIntExtra(EXTRA_ID,-1);
        mExName = findViewById(R.id.exercises_name);
        mAimOfExercise = findViewById(R.id.aim_of_exercise);
        mHawToPerformTheExercise = findViewById(R.id.haw_to_perform);

    }

    //Создание канала для уведомления
//        private void createChanel(){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//            {
//                String channelId = "test";
//                NotificationChannel channel = new NotificationChannel(channelId, "Channel human readable title",
//                        NotificationManager.IMPORTANCE_HIGH);
//                NotificationManager manager = getSystemService(NotificationManager.class);
//                manager.createNotificationChannel(channel);
//            }
//        }
//
//        //показ уведомления
//        private void startNotification(){
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY,14);
//            calendar.set(Calendar.MINUTE,40);
//            calendar.set(Calendar.SECOND, 0);
//
//             Intent intent = new Intent(HelpActivity.this, ReminderBroadcast.class);
//             PendingIntent pendingIntent = PendingIntent.getBroadcast(HelpActivity.this,0,
//                     intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//        }

    }

