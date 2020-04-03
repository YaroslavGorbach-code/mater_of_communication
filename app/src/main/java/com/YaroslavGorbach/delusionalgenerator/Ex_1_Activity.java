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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class Ex_1_Activity extends AppCompatActivity {

    private ImageButton mButtonStartPause;
    private Chronometer mChronometer;
    private long mPauseOffSet = 0;
    boolean mButtonState = false;
    private TextView mTextUnderThumb;
    private Button mButtonNextWorld;
    private TextView mWorld;
    private Button mButtonFinish;
    private Toolbar mToolbar;
    Random r = new Random();
    String [] mArrayTextUnderThumb = {};
    String [] mArrayWorlds = {};
    private ImageView mThumb;
    private LinearLayout mCenterLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_1);
        initializeComponents();
        //запуск секундомера
        mChronometer.start();
        //устанавливаем первое слово
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
            setThumbAndText();


        });

        mButtonFinish.setOnClickListener(v->{

            insertMyDateAndTime();
            finish();

        });
        mCenterLayout.setOnClickListener(v->{

            setWorld();
            setThumbAndText();

        });

    }

    private void initializeComponents(){
        mButtonStartPause = findViewById(R.id.button);
        mChronometer = findViewById(R.id.chronometer);
        mThumb = findViewById(R.id.thumb_iv);
        mTextUnderThumb = findViewById(R.id.textUnderThumb);
        mButtonNextWorld = findViewById(R.id.buttonNextWorld);
        mWorld = findViewById(R.id.world_tv);
        mArrayTextUnderThumb = getResources().getStringArray(R.array.TextUnderThumb_items_ex1);
        mArrayWorlds = getResources().getStringArray(R.array.Worlds_items_ex1);
        mButtonFinish = findViewById(R.id.buttonFinishEx1);
        mToolbar = findViewById(R.id.toolbar_ex1);
        mToolbar.inflateMenu(R.menu.menu_ex);
        mCenterLayout = findViewById(R.id.linearLayout_ex1);
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
        animateThumb();

    }

    private void insertMyDateAndTime(){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        //получаем время из секундомера в минутах
        long time = ((SystemClock.elapsedRealtime() - mChronometer.getBase())/1000) / 60;

        Repo.getInstance(this).insertDateAndTime(1, date, time);



    }
    private void animateThumb(){
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mThumb);
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mTextUnderThumb);
    }
}
