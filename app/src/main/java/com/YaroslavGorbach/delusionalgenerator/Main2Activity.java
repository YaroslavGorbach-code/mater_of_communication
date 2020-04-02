package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    private ImageButton mStartButton_ex1;
    private ImageButton mStartButton_ex2;
    private ImageButton mStartButton_ex3;
    private ImageButton mStartButton_ex4;
    private ImageButton mStartButton_ex5;
    private ImageButton mStartButton_ex6;

    private ImageButton mStatisticsButton_ex1;
    private ImageButton mStatisticsButton_ex2;
    private ImageButton mStatisticsButton_ex3;
    private ImageButton mStatisticsButton_ex4;
    private ImageButton mStatisticsButton_ex5;
    private ImageButton mStatisticsButton_ex6;

    private ImageButton mNotificationButton_ex1;
    private ImageButton mNotificationButton_ex2;
    private ImageButton mNotificationButton_ex3;
    private ImageButton mNotificationButton_ex4;
    private ImageButton mNotificationButton_ex5;
    private ImageButton mNotificationButton_ex6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializeComponents();

        mStartButton_ex1.setOnClickListener(v->{
            startActivity(new Intent(this, Ex_1_Activity.class).
                    putExtra(Ex_2_6Activity.EXTRA_ID_EX, 1));
        });

        mStartButton_ex2.setOnClickListener(v->{
            startActivity(new Intent(this, Ex_2_6Activity.class).
                    putExtra(Ex_2_6Activity.EXTRA_ID_EX, 2));
        });

        mStartButton_ex3.setOnClickListener(v->{
            startActivity(new Intent(this, Ex_2_6Activity.class).
                    putExtra(Ex_2_6Activity.EXTRA_ID_EX, 3));
        });

        mStartButton_ex4.setOnClickListener(v->{
            startActivity(new Intent(this, Ex_2_6Activity.class).
                    putExtra(Ex_2_6Activity.EXTRA_ID_EX, 4));
        });

        mStartButton_ex5.setOnClickListener(v->{
            startActivity(new Intent(this, Ex_2_6Activity.class).
                    putExtra(Ex_2_6Activity.EXTRA_ID_EX, 5));
        });

        mStartButton_ex6.setOnClickListener(v->{
            startActivity(new Intent(this, Ex_2_6Activity.class).
                    putExtra(Ex_2_6Activity.EXTRA_ID_EX, 6));
        });



        mStatisticsButton_ex1.setOnClickListener(v->{
            startActivity(new Intent(this, Statistics_activity.class).
                    putExtra(Statistics_activity.EXTRA_ID_EX, 1));
        });

        mStatisticsButton_ex2.setOnClickListener(v->{
            startActivity(new Intent(this, Statistics_activity.class).
                    putExtra(Statistics_activity.EXTRA_ID_EX, 2));
        });

        mStatisticsButton_ex3.setOnClickListener(v->{
            startActivity(new Intent(this, Statistics_activity.class).
                    putExtra(Statistics_activity.EXTRA_ID_EX, 3));
        });

        mStatisticsButton_ex4.setOnClickListener(v->{
            startActivity(new Intent(this, Statistics_activity.class).
                    putExtra(Statistics_activity.EXTRA_ID_EX, 4));
        });

        mStatisticsButton_ex5.setOnClickListener(v->{
            startActivity(new Intent(this, Statistics_activity.class).
                    putExtra(Statistics_activity.EXTRA_ID_EX, 5));
        });

        mStatisticsButton_ex6.setOnClickListener(v->{
            startActivity(new Intent(this, Statistics_activity.class).
                    putExtra(Statistics_activity.EXTRA_ID_EX, 6));
        });



        mNotificationButton_ex1.setOnClickListener(v->{
            startActivity(new Intent(this, HelpActivity.class).
                    putExtra(HelpActivity.EXTRA_ID, 1));
        });

        mNotificationButton_ex2.setOnClickListener(v->{
            startActivity(new Intent(this, HelpActivity.class).
                    putExtra(HelpActivity.EXTRA_ID, 2));
        });

        mNotificationButton_ex3.setOnClickListener(v->{
            startActivity(new Intent(this, HelpActivity.class).
                    putExtra(HelpActivity.EXTRA_ID, 3));
        });

        mNotificationButton_ex4.setOnClickListener(v->{
            startActivity(new Intent(this, HelpActivity.class).
                    putExtra(HelpActivity.EXTRA_ID, 4));
        });

        mNotificationButton_ex5.setOnClickListener(v->{
            startActivity(new Intent(this, HelpActivity.class).
                    putExtra(HelpActivity.EXTRA_ID, 5));
        });

        mNotificationButton_ex6.setOnClickListener(v->{
            startActivity(new Intent(this, HelpActivity.class).
                    putExtra(HelpActivity.EXTRA_ID, 6));
        });

    }

    private void initializeComponents(){
        mStartButton_ex1 = findViewById(R.id.start_ex1);
        mStartButton_ex2 = findViewById(R.id.start_ex2);
        mStartButton_ex3 = findViewById(R.id.start_ex3);
        mStartButton_ex4 = findViewById(R.id.start_ex4);
        mStartButton_ex5 = findViewById(R.id.start_ex5);
        mStartButton_ex6 = findViewById(R.id.start_ex6);

        mStatisticsButton_ex1 = findViewById(R.id.statistics_ex1);
        mStatisticsButton_ex2 = findViewById(R.id.statistics_ex2);
        mStatisticsButton_ex3 = findViewById(R.id.statistics_ex3);
        mStatisticsButton_ex4 = findViewById(R.id.statistics_ex4);
        mStatisticsButton_ex5 = findViewById(R.id.statistics_ex5);
        mStatisticsButton_ex6 = findViewById(R.id.statistics_ex6);

        mNotificationButton_ex1 = findViewById(R.id.notification_ex1);
        mNotificationButton_ex2 = findViewById(R.id.notification_ex2);
        mNotificationButton_ex3 = findViewById(R.id.notification_ex3);
        mNotificationButton_ex4 = findViewById(R.id.notification_ex4);
        mNotificationButton_ex5 = findViewById(R.id.notification_ex5);
        mNotificationButton_ex6 = findViewById(R.id.notification_ex6);

    }
}

