package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    private Chronometer mChronometer_allTime;
    private Chronometer mChronometer_1worldTime;
    private long mPauseOffSet = 0;
    private long mWorldTimePauseOffSet = 0;
    boolean mButtonState = false;
    private Button mButtonNextWorld;
    private TextView mWorld;
    private Button mButtonFinish;
    private Toolbar mToolbar;
    Random r = new Random();
    int mIdEx;
    private ImageView mThumb;
    private TextView mTextUnderThumb;
    private TextView mShort_des;

   private String [] mArrayTextUnderThumb = {};
   private String [] mArrayWorlds_ex1 = {};
   private String [] mArrayWorlds_ex2_living = {};
   private String [] mArrayWorlds_ex2_not_living = {};
   private String [] mArrayWorlds_ex3_filings = {};
   private String [] mArrayWorlds_ex4 = {};
   private String [] mArrayWorlds_ex5 = {};
   private String [] mArrayWorlds_ex6 = {};
   private String [] mArrayWorlds_ex7 = {};
   private String [] mArrayWorlds_ex8 = {};

    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises2);
        initializeComponents();
        mChronometer_allTime.start();
        mChronometer_1worldTime.start();


        switch (mIdEx){

            case 1:
                mArrayWorlds_ex1 = getResources().getStringArray(R.array.Worlds_items_ex1);
                mArrayTextUnderThumb = getResources().getStringArray(R.array.TextUnderThumb_ex1);
                mToolbar.setTitle("Лингвистические пирамиды");
                mTextUnderThumb.setVisibility(View.VISIBLE);
                mThumb.setVisibility(View.VISIBLE);
                mShort_des.setText("Обобщайте, разобобщайте, и переходите по аналогиям.");
                break;


            case 2:
                mArrayWorlds_ex2_living = getResources().getStringArray(R.array.Worlds_items_ex2_living);
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("Чем ворон похож на стул");
                mShort_des.setText("Найдите сходство.");
                hideThumb();
            break;

            case 3:
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_ex1);
                mArrayWorlds_ex3_filings = getResources().getStringArray(R.array.Worlds_items_ex3_filings);
                mToolbar.setTitle("Чем ворон похож на стул (чувства)");
                mShort_des.setText("Найдите сходство.");
                hideThumb();
                break;

            case 4:
                mArrayWorlds_ex4 = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("Продвинутое сявязывание");
                mShort_des.setText("Найдите сходства.");
                hideThumb();
                break;

            case 5:
                mArrayWorlds_ex5 = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("О чем вижу, о том и пою");
                mShort_des.setText("Говорите максимально долго об этом.");
                hideThumb();
                break;

            case 6:
                mArrayWorlds_ex6 = getResources().getStringArray(R.array.Worlds_items_ex6);
                mToolbar.setTitle("Другие варианты сокращений");
                mShort_des.setText("Придумайте необычную расшифровку.");
                hideThumb();
                break;
            case 7:
                mArrayWorlds_ex7 = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("Волшебный нейминг");
                mShort_des.setText("Придумайте к этому слову 5 или больше смешных прилагательных.");
                hideThumb();
                break;
            case 8:
                mArrayWorlds_ex8 = getResources().getStringArray(R.array.Worlds_items_ex1);
                mToolbar.setTitle("Купля-продажа");
                mShort_des.setText("Продайте это.");
                hideThumb();
                break;
        }


        mToolbar.setOnMenuItemClickListener(v->{
                    startActivity(new Intent(this, HelpActivity.class)
                            .putExtra(HelpActivity.EXTRA_ID, mIdEx));

                    if (!mButtonState){
                        mButtonStartPause.setImageResource(R.drawable.ic_play_arrow50dp);
                        mButtonState = true;
                        mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer_allTime.getBase();
                        mWorldTimePauseOffSet = SystemClock.elapsedRealtime() - mChronometer_1worldTime.getBase();
                        mChronometer_1worldTime.stop();
                        mChronometer_allTime.stop();
                        mButtonNextWorld.setVisibility(View.INVISIBLE);
                        mButtonFinish.setVisibility(View.VISIBLE);
                    }


            return true;
        });

        mToolbar.setNavigationOnClickListener(v->{
            finish();
        });

        mButtonStartPause.setOnClickListener(v->{

            if(mButtonState){

                mButtonStartPause.setImageResource(R.drawable.ic_pause);
                mButtonState = false;
                mChronometer_allTime.setBase(SystemClock.elapsedRealtime() - mPauseOffSet);
                mChronometer_allTime.start();
                mChronometer_1worldTime.setBase(SystemClock.elapsedRealtime() - mWorldTimePauseOffSet);
                mChronometer_1worldTime.start();
                mButtonFinish.setVisibility(View.INVISIBLE);
                mButtonNextWorld.setVisibility(View.VISIBLE);

            }else{

                mButtonStartPause.setImageResource(R.drawable.ic_play_arrow50dp);
                mButtonState = true;
                mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer_allTime.getBase();
                mWorldTimePauseOffSet = SystemClock.elapsedRealtime() - mChronometer_1worldTime.getBase();
                mChronometer_allTime.stop();
                mChronometer_1worldTime.stop();
                mButtonNextWorld.setVisibility(View.INVISIBLE);
                mButtonFinish.setVisibility(View.VISIBLE);

            }

        });

        mThumb.setOnClickListener(v->{
            setThumbAndText();
            animateThumb();
        });

        mButtonNextWorld.setOnClickListener(v->{

            setWorld();
            mChronometer_1worldTime.setBase(SystemClock.elapsedRealtime());


        });

        mButtonFinish.setOnClickListener(v->{
            insertMyDateAndTime();
            finish();
        });

        setWorld();

    }

    private void initializeComponents(){
        mButtonStartPause = findViewById(R.id.button);
        mChronometer_allTime = findViewById(R.id.chronometer_allTime);
        mButtonNextWorld = findViewById(R.id.buttonNextWorld);
        mWorld = findViewById(R.id.world_tv);
        mButtonFinish = findViewById(R.id.buttonFinishEx1);
        mToolbar = findViewById(R.id.toolbar_ex1);
        mToolbar.inflateMenu(R.menu.menu_ex_desk);
        mIdEx = getIntent().getIntExtra(EXTRA_ID_EX, -1);
        mThumb = findViewById(R.id.thumb_iv);
        mTextUnderThumb = findViewById(R.id.textUnderThumb);
        mShort_des = findViewById(R.id.description_short);
        mChronometer_1worldTime = findViewById(R.id.chronometer_1world_time);

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

            case 7:
                mWorld.setText(mArrayWorlds_ex7[r.nextInt(mArrayWorlds_ex7.length)]);
                break;

            case 8:
                mWorld.setText(mArrayWorlds_ex8[r.nextInt(mArrayWorlds_ex8.length)]);
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

    private void hideThumb(){
        mWorld.setGravity(Gravity.CENTER | Gravity.BOTTOM);
    }

    private void animateThumb(){
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mThumb);
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mTextUnderThumb);
    }

    private void setTheme(){
        String color = Repo.getInstance(ExercisesActivity.this).getThemeState();
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

    private void insertMyDateAndTime(){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        //получаем время из секундомера в минутах
        long time = ((SystemClock.elapsedRealtime() - mChronometer_allTime.getBase())/1000) / 60;

        Repo.getInstance(this).insertDateAndTime(mIdEx, date, time);



    }

}

