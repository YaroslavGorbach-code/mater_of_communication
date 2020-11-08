package com.YaroslavGorbach.delusionalgenerator.Fragments;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class ExercisesCategory1Fragment extends Fragment {
    private ImageButton mButtonStartPause;
    private Chronometer mChronometer_allTime;
    private Chronometer mChronometer_1worldTime;
    private long mPauseOffSet = 0;
    private long mWorldTimePauseOffSet = 0;
    private boolean mButtonState = false;
    private Button mButtonNextWorld;
    private TextView mWorld;
    private Button mButtonFinish;
    private Random r = new Random();
    private int mIdEx;
    private ImageView mThumb;
    private TextView mTextUnderThumb;
    private TextView mShort_des;
    private RelativeLayout mThumbAndText;
    private TextView mWorldCounter;
    private int mWorldCount;
    private int mMaxWorldCount;
    private boolean mIsRecording = false;
    private Button mStartRecordingButton;
    private static final String recordPermission = Manifest.permission.RECORD_AUDIO;
    private static final int PERMISSION_CODE = 21;
    private MediaRecorder mediaRecorder;
    private String recordFile;
    private String mExName;

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


    private static final String ARG_ID_EX = "ARG_ID_EX";

    public interface FinishEx{
        void onFinish();
    }

    public ExercisesCategory1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param exId Parameter 1.
     * @return A new instance of fragment ExerciseDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExercisesCategory1Fragment newInstance(int exId) {
        ExercisesCategory1Fragment fragment = new ExercisesCategory1Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_EX, exId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIdEx = getArguments().getInt(ARG_ID_EX, -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercises, container, false);

        mButtonStartPause = view.findViewById(R.id.button);
        mChronometer_allTime = view.findViewById(R.id.chronometer_allTime);
        mButtonNextWorld = view.findViewById(R.id.buttonNextWorld);
        mWorld = view.findViewById(R.id.world_tv);
        mButtonFinish = view.findViewById(R.id.buttonFinishEx1);

        mThumb = view.findViewById(R.id.thumb_iv);
        mTextUnderThumb = view.findViewById(R.id.textUnderThumb);
        mShort_des = view.findViewById(R.id.description_short);
        mChronometer_1worldTime = view.findViewById(R.id.chronometer_1world_time);
        mThumbAndText = view.findViewById(R.id.thumbAndText);
        mWorldCounter = view.findViewById(R.id.world_counter);
        mStartRecordingButton = view.findViewById(R.id.buttonStartRecording);


        /*Поиск всех view и запуск секундомера*/
        mChronometer_allTime.start();
        mChronometer_1worldTime.start();

        //установка максимального зщначения для счетчика слов
        mMaxWorldCount = Repo.getInstance(getContext()).getMaxWorldCount(mIdEx);
        mWorldCounter.setText(String.format("%s/%s", mWorldCount, mMaxWorldCount));


        /*В зависимости от айди упражнения мы выбираем потходяшии слова и иницыализируем наши view*/
        switch (mIdEx){

            case 1:
                mArrayWorlds_ex1 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayTextUnderThumb = getResources().getStringArray(R.array.TextUnderThumb_ex1);
                mExName = "Лингвистические пирамиды";
                mThumbAndText.setVisibility(View.VISIBLE);
                mShort_des.setText("Обобщайте, разобобщайте и переходите по аналогиям");
                break;


            case 2:
                mArrayWorlds_ex2_living = getResources().getStringArray(R.array.Worlds_items_Alive);
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mExName = "Чем ворон похож на стул";
                mShort_des.setText("Найдите сходство");
                break;

            case 3:
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayWorlds_ex3_filings = getResources().getStringArray(R.array.Worlds_items_filings);
                mExName = "Чем ворон похож на стул (чувства)";
                mShort_des.setText("Найдите сходство");
                break;

            case 4:
                mArrayWorlds_ex4 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mExName = "Продвинутое сявязывание";
                mShort_des.setText("Найдите сходства");
                break;

            case 5:
                mArrayWorlds_ex5 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mExName = "О чем вижу, о том и пою";
                mShort_des.setText("Говорите максимально долго об этом");
                break;

            case 6:
                mArrayWorlds_ex6 = getResources().getStringArray(R.array.Worlds_items_abbreviations);
                mExName = "Другие варианты сокращений";
                mShort_des.setText("Придумайте необычную расшифровку");
                break;
            case 7:
                mArrayWorlds_ex7 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mExName = "Волшебный нейминг";
                mShort_des.setText("Придумайте к этому слову 5 или больше смешных прилагательных");
                break;
            case 8:
                mArrayWorlds_ex8 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mExName = "Купля-продажа";
                mShort_des.setText("Продайте это");
                break;
            case 9:
                mArrayWorlds_ex9 = getResources().getStringArray(R.array.letters);
                mExName = "Вспомнить все";
                mShort_des.setText("Назовите 15 слов, которые начинаются с этой буквы");
                break;
            case 10:
                mArrayWorlds_ex10 = getResources().getStringArray(R.array.Terms);
                mExName = "В соавторстве с Далем";
                mShort_des.setText("Дайте определение слову");
                break;
            case 11:
                mArrayWorlds_ex11 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mExName = "Тест Роршаха";
                mShort_des.setText("Придумайте чем это может быть еще");
                break;
            case 12:
                mArrayWorlds_ex12 = getResources().getStringArray(R.array.professions);
                mExName = "Хуже уже не будет";
                mShort_des.setText("Придумайте ситуацию или фразу худшего в мире");
                break;
        }


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

        /*Оброботка нажатия на кнопку начать и остановить запись голоса*/
        mStartRecordingButton.setOnClickListener(v -> {
            if (mIsRecording){
                stopRecording();

            }else {
                if(checkRecordPermission()){
                    startRecording();
                    mStartRecordingButton.setClickable(false);
                    new Handler().postDelayed(() -> mStartRecordingButton.setClickable(true), 1000);
                }
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
            mWorldCounter.setText(String.format("%s/%s", mWorldCount, mMaxWorldCount));
        });

        /*Завершение упражнения при нажатии */
        mButtonFinish.setOnClickListener(v->{
                getActivity().finish();
            });

        setWorld();

        return view;
    }




    private boolean checkRecordPermission() {
        //Check permission
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), recordPermission) == PackageManager.PERMISSION_GRANTED) {
            //Permission Granted
            return true;
        } else {
            //Permission not granted, ask for permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }

    /*Остановка записи*/
    private void stopRecording() {
        mStartRecordingButton.setText("Начать запись");
        mIsRecording = false;
        Toast.makeText(getContext(), "Запись сохранена: " + recordFile, Toast.LENGTH_SHORT).show();
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    /*Старт записи*/
    private void startRecording() {

        mStartRecordingButton.setText("Остановить запись");
        mIsRecording = true;

        //Get app external directory path
        String recordPath = Objects.requireNonNull(getActivity()).getExternalFilesDir("/").getAbsolutePath();

        //Get current date and time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.getDefault());
        Date now = new Date();

        //initialize filename variable with date and time at the end to ensure the new file wont overwrite previous file
        recordFile = mExName + " " + formatter.format(now) + ".3gp";

        //filenameText.setText("Recording, File Name : " + recordFile);

        //Setup Media Recorder for recording
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start Recording
        mediaRecorder.start();
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



    /*Метод используеться для ведения статистики*/
    private void insertMyDateAndTime(){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        //получаем время из секундомера в минутах
        long time = ((SystemClock.elapsedRealtime() - mChronometer_allTime.getBase())/1000) / 60;

        Repo.getInstance(getContext()).insertDateAndTime(mIdEx, date, time);
        Repo.getInstance(getContext()).insertDateAndCountWorlds(mIdEx, date, mWorldCount);

    }


    @Override
    public void onStop() {
            super.onStop();
            if(mIsRecording){
                stopRecording();
            }
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        insertMyDateAndTime();
        mButtonStartPause.setImageResource(R.drawable.ic_play_arrow50dp);
        mButtonState = true;
        mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer_allTime.getBase();
        mWorldTimePauseOffSet = SystemClock.elapsedRealtime() - mChronometer_1worldTime.getBase();
        mChronometer_allTime.stop();
        mChronometer_1worldTime.stop();
        mButtonNextWorld.setVisibility(View.INVISIBLE);
        mButtonFinish.setVisibility(View.VISIBLE);
        if(mIsRecording){
            stopRecording();
        }
    }
}


