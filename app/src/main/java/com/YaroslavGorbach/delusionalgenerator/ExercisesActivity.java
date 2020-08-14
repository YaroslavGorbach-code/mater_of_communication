package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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
    private boolean mButtonState = false;
    private Button mButtonNextWorld;
    private TextView mWorld;
    private Button mButtonFinish;
    private Toolbar mToolbar;
    private Random r = new Random();
    private int mIdEx;
    private ImageView mThumb;
    private TextView mTextUnderThumb;
    private TextView mShort_des;
    private RelativeLayout mThumbAndText;
    private TextView mWorldCounter;
    private int mWorldCount;

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
   private String [] mArrayWorlds_ex9 = {};
   private String [] mArrayWorlds_ex10 = {};
   private String [] mArrayWorlds_ex11 = {};
   private String [] mArrayWorlds_ex12 = {};


    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        AdView mAdView;
        mAdView = findViewById(R.id.adViewTab1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*Поиск всех view и запуск секундомера*/
        initializeComponents();
        mChronometer_allTime.start();
        mChronometer_1worldTime.start();


        /*В зависимости от айди упражнения мы выбираем потходяшии слова и иницыализируем наши view*/
        switch (mIdEx){

            case 1:
                mArrayWorlds_ex1 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayTextUnderThumb = getResources().getStringArray(R.array.TextUnderThumb_ex1);
                mToolbar.setTitle("Лингвистические пирамиды");
                mThumbAndText.setVisibility(View.VISIBLE);
                mShort_des.setText("Обобщайте, разобобщайте, и переходите по аналогиям");
                break;


            case 2:
                mArrayWorlds_ex2_living = getResources().getStringArray(R.array.Worlds_items_Alive);
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mToolbar.setTitle("Чем ворон похож на стул");
                mShort_des.setText("Найдите сходство");
            break;

            case 3:
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayWorlds_ex3_filings = getResources().getStringArray(R.array.Worlds_items_filings);
                mToolbar.setTitle("Чем ворон похож на стул (чувства)");
                mShort_des.setText("Найдите сходство");
                break;

            case 4:
                mArrayWorlds_ex4 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mToolbar.setTitle("Продвинутое сявязывание");
                mShort_des.setText("Найдите сходства");
                break;

            case 5:
                mArrayWorlds_ex5 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mToolbar.setTitle("О чем вижу, о том и пою");
                mShort_des.setText("Говорите максимально долго об этом");
                break;

            case 6:
                mArrayWorlds_ex6 = getResources().getStringArray(R.array.Worlds_items_abbreviations);
                mToolbar.setTitle("Другие варианты сокращений");
                mShort_des.setText("Придумайте необычную расшифровку");
                break;
            case 7:
                mArrayWorlds_ex7 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mToolbar.setTitle("Волшебный нейминг");
                mShort_des.setText("Придумайте к этому слову 5 или больше смешных прилагательных");
                break;
            case 8:
                mArrayWorlds_ex8 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mToolbar.setTitle("Купля-продажа");
                mShort_des.setText("Продайте это");
                break;
            case 9:
                mArrayWorlds_ex9 = getResources().getStringArray(R.array.letters);
                mToolbar.setTitle("Вспомнить все");
                mShort_des.setText("Назовите 15 слов, которые начинаются с этой буквы");
                break;
            case 10:
                mArrayWorlds_ex10 = getResources().getStringArray(R.array.Terms);
                mToolbar.setTitle("В соавторстве с Далем");
                mShort_des.setText("Дайте определение слову");
                break;
            case 11:
                mArrayWorlds_ex11 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mToolbar.setTitle("Тест Роршаха");
                mShort_des.setText("Придумайте чем ето может быть еще");
                break;
            case 12:
                mArrayWorlds_ex12 = getResources().getStringArray(R.array.professions);
                mToolbar.setTitle("Хуже уже не будет");
                mShort_des.setText("Придумайте ситуацию или фразу худшего в мире");
                break;
        }


        /*Оброботка нажатия на кнопку помощи по упражнению.*/
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

        /*Остановка активити при нажатии на стрелку назад*/
        mToolbar.setNavigationOnClickListener(v->{
            finish();
        });

        /*Оброботка нажатий на кнопки запуска и остановки секундомера*/
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

        /*При нажатии на большой палец смена его состояния*/
        mThumb.setOnClickListener(v->{
            setThumbAndText();
            animateThumb();
        });

        /*Установка нового слова и обнуление секундомера
        который показывает время потраченое на одно слово */
        mButtonNextWorld.setOnClickListener(v->{
            setWorld();
            mChronometer_1worldTime.setBase(SystemClock.elapsedRealtime());
            mWorldCount++;
            mWorldCounter.setText(String.format("%s/%s", mWorldCount, 0));
        });

        /*Завершение упражнения при нажатии */
        mButtonFinish.setOnClickListener(v->{
            finish();
        });

        setWorld();
    }

    /*поиск всех view*/
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
        mThumbAndText = findViewById(R.id.thumbAndText);
        mWorldCounter = findViewById(R.id.world_counter);

    }


    /*В зависимости от айди упражнения устанавливаем в textView правельное слово или пару слов*/
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

            case 9:
                mWorld.setText(mArrayWorlds_ex9[r.nextInt(mArrayWorlds_ex9.length)]);
                break;

            case 10:
                mWorld.setText(mArrayWorlds_ex10[r.nextInt(mArrayWorlds_ex10.length)]);
                break;

            case 11:
                mWorld.setText(mArrayWorlds_ex11[r.nextInt(mArrayWorlds_ex11.length)]);
                break;

            case 12:
                mWorld.setText(mArrayWorlds_ex12[r.nextInt(mArrayWorlds_ex12.length)]);
                break;

        }
    }

    /*Уставнока значения борльшого пальца*/
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


    /*Анимацыя для большого пальца*/
    private void animateThumb(){
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mThumb);
        YoYo.with(Techniques.RotateIn)
                .duration(500)
                .playOn(mTextUnderThumb);
    }

    /*Установка темы*/
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

    /*Метод используеться для ведения статистики*/
    private void insertMyDateAndTime(){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        //получаем время из секундомера в минутах
        long time = ((SystemClock.elapsedRealtime() - mChronometer_allTime.getBase())/1000) / 60;

        Repo.getInstance(this).insertDateAndTime(mIdEx, date, time);



    }

    @Override
    protected void onStop() {
        super.onStop();
        insertMyDateAndTime();
    }
}


