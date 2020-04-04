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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ExercisesActivity extends AppCompatActivity {
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
    private ImageView mThumb;
    private TextView mTextUnderThumb;

    String [] mArrayTextUnderThumb = {};
    String [] mArrayWorlds_ex1 = {};
    String [] mArrayWorlds_ex2_living = {};
    String [] mArrayWorlds_ex2_not_living = {};
    String [] mArrayWorlds_ex3_filings = {};
    String [] mArrayWorlds_ex4 = {};
    String [] mArrayWorlds_ex5 = {};
    String [] mArrayWorlds_ex6 = {};

    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        initializeComponents();
        mChronometer.start();


        switch (mIdEx){

            case 1:{
                mArrayWorlds_ex1 = getResources().getStringArray(R.array.Worlds_items_ex1);
                mArrayTextUnderThumb = getResources().getStringArray(R.array.TextUnderThumb_ex1);
                mToolbar.setTitle("Лингвистические пирамиды");
                mTextUnderThumb.setVisibility(View.VISIBLE);
                mThumb.setVisibility(View.VISIBLE);
                break;
            }

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
                mArrayWorlds_ex4 = getResources().getStringArray(R.array.Worlds_items_ex4);
                mToolbar.setTitle("Продвинутое сявязывание");
                break;

            case 5:
                mArrayWorlds_ex5 = getResources().getStringArray(R.array.Worlds_items_ex5);
                mToolbar.setTitle("О чем вижу, о том и пою");
                break;

            case 6:
                mArrayWorlds_ex6 = getResources().getStringArray(R.array.Worlds_items_ex6);
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
            insertMyDateAndTime();
            finish();
        });

        setWorld();
        
    }

    private void initializeComponents(){
        mButtonStartPause = findViewById(R.id.button);
        mChronometer = findViewById(R.id.chronometer);
        mButtonNextWorld = findViewById(R.id.buttonNextWorld);
        mWorld = findViewById(R.id.world_tv);
        mButtonFinish = findViewById(R.id.buttonFinishEx1);
        mToolbar = findViewById(R.id.toolbar_ex1);
        mToolbar.inflateMenu(R.menu.menu_ex);
        mIdEx = getIntent().getIntExtra(EXTRA_ID_EX, -1);
        mThumb = findViewById(R.id.thumb_iv);
        mTextUnderThumb = findViewById(R.id.textUnderThumb);

    }



    private void setWorld(){

        switch (mIdEx) {

            case 1:
                mWorld.setText(mArrayWorlds_ex1[r.nextInt(mArrayWorlds_ex1.length)]);
                setThumbAndText();
                animateThumb();
                break;

            case 2:
                mWorld.setText(String.format("%s - %s", mArrayWorlds_ex2_living[r.nextInt(mArrayWorlds_ex2_living.length)],
                        mArrayWorlds_ex2_not_living[r.nextInt(mArrayWorlds_ex2_not_living.length)]));
                break;

            case 3:
                mWorld.setText(String.format("%s - %s", mArrayWorlds_ex3_filings[r.nextInt(mArrayWorlds_ex3_filings.length)],
                        mArrayWorlds_ex2_not_living[r.nextInt(mArrayWorlds_ex2_not_living.length)]));
                break;


            case 4:
                String worl_1 = mArrayWorlds_ex4[r.nextInt(mArrayWorlds_ex4.length)];
                String worl_2 = mArrayWorlds_ex4[r.nextInt(mArrayWorlds_ex4.length)];
                if (worl_1.equals(worl_2)) {
                    worl_1 = mArrayWorlds_ex4[r.nextInt(mArrayWorlds_ex4.length)];
                }
                mWorld.setText(String.format("%s - %s", worl_1, worl_2));
                break;

            case 5:
                mWorld.setText(mArrayWorlds_ex5[r.nextInt(mArrayWorlds_ex5.length)]);
                break;

            case 6:
                mWorld.setText(mArrayWorlds_ex6[r.nextInt(mArrayWorlds_ex6.length)]);
                break;

        }
    }

    private void setThumbAndText(){

        mTextUnderThumb.setText(mArrayTextUnderThumb[r.nextInt(mArrayTextUnderThumb.length)]);
        if (mTextUnderThumb.getText().equals("Аналогия")){

            mThumb.setImageResource(R.drawable.ic_right);
        }else if(mTextUnderThumb.getText().equals("Разобобщения")){

            mThumb.setImageResource(R.drawable.ic_down);
        }else if(mTextUnderThumb.getText().equals("Обобщение")){

            mThumb.setImageResource(R.drawable.ic_up);
        }

    }

    private void animateThumb(){
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mThumb);
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mTextUnderThumb);
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

