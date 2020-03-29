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
    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_2);
        initializeComponents();
        mChronometer.start();

        switch (mIdEx){
            case 2:
                mArrayWorlds = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("Чем ворон похож на стул");
            break;

            case 3:
                mArrayWorlds = getResources().getStringArray(R.array.Worlds_items_ex1);
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

        mWorld.setText(mArrayWorlds[r.nextInt(47)]);

    }

}

