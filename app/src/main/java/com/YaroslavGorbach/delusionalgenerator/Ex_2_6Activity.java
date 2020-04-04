package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Ex_2_6Activity extends AppCompatActivity {
    private ImageButton mButtonStartPause;
    private Chronometer mChronometer;
    private long mPauseOffSet = 0;
    boolean mButtonState = false;
    private Button mButtonNextWorld;
    private TextView mWorld;
    private Button mButtonFinish;
    private Toolbar mToolbar;
    Random r = new Random();
    int mIdEx;
    String [] mArrayWorlds = {};
    String [] mArrayWorlds_ex2_living = {};
    String [] mArrayWorlds_ex2_not_living = {};
    String [] mArrayWorlds_ex3_filings = {};

    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_2);
        initializeComponents();
        mChronometer.start();


        switch (mIdEx){
            case 2:
                mArrayWorlds_ex2_living = getResources().getStringArray(R.array.Worlds_items_ex2_living);
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_ex2_not_living);
                mToolbar.setTitle("Чем ворон похож на стул");
            break;

            case 3:
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_ex2_not_living);
                mArrayWorlds_ex3_filings = getResources().getStringArray(R.array.Worlds_items_ex3_filings);
                mToolbar.setTitle("Чем ворон похож на стул 2");
                break;

            case 4:
                mArrayWorlds = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("Продвинутое сявязывание");
                break;

            case 5:
                mArrayWorlds = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("О чем вижу, о том и пою");
                break;

            case 6:
                mArrayWorlds = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("Другие варианты сокращений");
                break;
        }
        setWorld();



        mToolbar.setOnMenuItemClickListener(v->{

            // TODO: 29.03.2020 Открытие помощи по упражнению
            return true;

        });

        mToolbar.setNavigationOnClickListener(v->{
            finish();
        });

        mButtonStartPause.setOnClickListener(v->{

            if(mButtonState){

                mButtonStartPause.setImageResource(R.drawable.ic_pause);
                mButtonState = false;
                mChronometer.setBase(SystemClock.elapsedRealtime() - mPauseOffSet);
                mChronometer.start();
                mButtonFinish.setVisibility(View.INVISIBLE);
                mButtonNextWorld.setVisibility(View.VISIBLE);

            }else{

                mButtonStartPause.setImageResource(R.drawable.ic_play_arrow50dp);
                mButtonState = true;
                mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer.getBase();
                mChronometer.stop();
                mButtonNextWorld.setVisibility(View.INVISIBLE);
                mButtonFinish.setVisibility(View.VISIBLE);

            }

        });

        mButtonNextWorld.setOnClickListener(v->{

            setWorld();

        });

        mButtonFinish.setOnClickListener(v->{
            insertMyDateAndTime();
            finish();
        });

    }

    private void initializeComponents(){
        mButtonStartPause = findViewById(R.id.button_2);
        mChronometer = findViewById(R.id.chronometer_2);
        mButtonNextWorld = findViewById(R.id.buttonNextWorld_2);
        mWorld = findViewById(R.id.world_tv_2);
        mButtonFinish = findViewById(R.id.buttonFinishEx2);
        mToolbar = findViewById(R.id.toolbar_ex2);
        mToolbar.inflateMenu(R.menu.menu_ex);
        mIdEx = getIntent().getIntExtra(EXTRA_ID_EX, -1);

    }



    private void setWorld(){

        if (mIdEx == 2){

            mWorld.setText(String.format("%s - %s", mArrayWorlds_ex2_living[r.nextInt(90)],
                    mArrayWorlds_ex2_not_living[r.nextInt(90)]));

        } else if(mIdEx == 3){

            mWorld.setText(String.format("%s - %s", mArrayWorlds_ex3_filings[r.nextInt(60)],
                    mArrayWorlds_ex2_not_living[r.nextInt(90)]));

        }
        else {

            mWorld.setText( mArrayWorlds[r.nextInt(90)]);

        }


    }

    private void insertMyDateAndTime(){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        //получаем время из секундомера в минутах
        long time = ((SystemClock.elapsedRealtime() - mChronometer.getBase())/1000) / 60;

        Repo.getInstance(this).insertDateAndTime(mIdEx, date, time);



    }

}

