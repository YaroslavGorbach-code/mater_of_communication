package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class Ex_1_Activity extends AppCompatActivity {

    private ImageButton mButtonStartPause;
    private Chronometer mChronometer;
    private long mPauseOffSet = 0;
    boolean mButtonState = true;
    private TextView mTextUnderThumb;
    private Button mButtonNextWorld;
    private TextView mWorld;
    Random r = new Random();
    String [] mArrayTextUnderThumb = {};
    String [] mArrayWorlds = {};
    private ImageView mThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_1);
        mButtonStartPause = findViewById(R.id.button);
        mChronometer = findViewById(R.id.chronometer);
        mThumb = findViewById(R.id.thumb_iv);
        mTextUnderThumb = findViewById(R.id.textUnderThumb);
        mButtonNextWorld = findViewById(R.id.buttonNextWorld);
        mWorld = findViewById(R.id.world_tv);

        mArrayTextUnderThumb = getResources().getStringArray(R.array.TextUnderThumb_items);
        mArrayWorlds = getResources().getStringArray(R.array.Worlds_items);


        mButtonStartPause.setOnClickListener(v->{

            if(mButtonState){

                mButtonStartPause.setImageResource(R.drawable.ic_pause);
                mButtonState = false;
                mChronometer.setBase(SystemClock.elapsedRealtime() - mPauseOffSet);
                mChronometer.start();
                showOrHideButtons(true);
                setThumbAndText();
                setWorld();




            }else{

                mButtonStartPause.setImageResource(R.drawable.ic_play);
                mButtonState = true;
                mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer.getBase();
                mChronometer.stop();
                mButtonNextWorld.setVisibility(View.INVISIBLE);

            }

        });

        mButtonNextWorld.setOnClickListener(v->{

            setWorld();
            setThumbAndText();


        });

    }

    private void setThumbAndText(){

        mTextUnderThumb.setText(mArrayTextUnderThumb[r.nextInt(3)]);
        if (mTextUnderThumb.getText().equals("Аналогия")){

            mThumb.setImageResource(R.drawable.ic_right);
        }else if(mTextUnderThumb.getText().equals("Разобобщения")){

            mThumb.setImageResource(R.drawable.ic_down);
        }else if(mTextUnderThumb.getText().equals("Обобщение")){

            mThumb.setImageResource(R.drawable.ic_up);
        }

    }

    private void setWorld(){

        mWorld.setText(mArrayWorlds[r.nextInt(47)]);

    }

    private void showOrHideButtons(boolean buttonState){

        if (buttonState){

            mThumb.setVisibility(View.VISIBLE);
            mTextUnderThumb.setVisibility(View.VISIBLE);
            mButtonNextWorld.setVisibility(View.VISIBLE);
            mWorld.setVisibility(View.VISIBLE);

        }else {

            mThumb.setVisibility(View.INVISIBLE);
            mTextUnderThumb.setVisibility(View.INVISIBLE);
            mButtonNextWorld.setVisibility(View.INVISIBLE);
            mWorld.setVisibility(View.INVISIBLE);

        }
    }

}
