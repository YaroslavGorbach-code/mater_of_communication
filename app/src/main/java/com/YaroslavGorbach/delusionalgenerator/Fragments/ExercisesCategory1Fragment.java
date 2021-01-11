package com.YaroslavGorbach.delusionalgenerator.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Layout;
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

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExerciseCategory1ViewModel;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.Helpers.AdMob;
import com.YaroslavGorbach.delusionalgenerator.Helpers.DateAndTime;
import com.YaroslavGorbach.delusionalgenerator.Helpers.Permissions;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class ExercisesCategory1Fragment extends Fragment {
    private ImageButton mButtonStartPause;
    private Chronometer mChronometer_allTime;
    private Chronometer mChronometer_1worldTime;
    private long mPauseOffSet = 0;
    private long mWorldTimePauseOffSet = 0;
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
    private Button mStartRecordingButton;


    private String mExName;
    private MaterialToolbar mMaterialToolbar;
    private LiveData<Exercise> mEx;

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
   private String [] mArrayWorlds_ex13 = {};
   private Set<String> mSetWorlds_ex14 = new LinkedHashSet<>();
   private ExerciseCategory1ViewModel mViewModel;
   private boolean mChronometerState;


    public static ExercisesCategory1Fragment newInstance() {
        return new ExercisesCategory1Fragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category_1, container, false);
        /*Поиск всех view и запуск секундомера*/
        mButtonStartPause = view.findViewById(R.id.button);
        mChronometer_allTime = view.findViewById(R.id.chronometer_allTime);
        mButtonNextWorld = view.findViewById(R.id.buttonNextWorld);
        mWorld = view.findViewById(R.id.world_tv);
        mButtonFinish = view.findViewById(R.id.buttonFinishEx1);
        mMaterialToolbar = view.findViewById(R.id.toolbar_ex_category_1);
        mMaterialToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mThumb = view.findViewById(R.id.thumb_iv);
        mTextUnderThumb = view.findViewById(R.id.textUnderThumb);
        mShort_des = view.findViewById(R.id.description_short);
        mChronometer_1worldTime = view.findViewById(R.id.chronometer_1world_time);
        mThumbAndText = view.findViewById(R.id.thumbAndText);
        mWorldCounter = view.findViewById(R.id.world_counter);
        mStartRecordingButton = view.findViewById(R.id.buttonStartRecording);
        mIdEx = ExercisesCategory1FragmentArgs.fromBundle(requireArguments()).getIdEx();

        mViewModel = new ViewModelProvider(this).get(ExerciseCategory1ViewModel.class);

        mEx = mViewModel.getExerciseById(mIdEx);

        //установка максимального зщначения для счетчика слов
        mMaxWorldCount = Repo.getInstance(getContext()).getMaxWorldCount(mIdEx);
        mWorldCounter.setText(String.format("%s/%s", mWorldCount, mMaxWorldCount));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*запуск секундомера*/
        startChronometer();

        /*В зависимости от айди упражнения мы выбираем потходяшии слова и иницыализируем наши view*/
        getWordsByExId();
        setWorld();

        /*показ банера*/
        AdMob.showBanner(view.findViewById(R.id.adViewTabEx1));

        /*навигация назад*/
        mMaterialToolbar.setNavigationOnClickListener(v ->
                Navigation.findNavController(view).popBackStack());

        mEx.observe(getViewLifecycleOwner(), exercise ->
                mExName = exercise.name);

        /*Оброботка нажатий на кнопки запуска и остановки секундомера*/
        mButtonStartPause.setOnClickListener(v->{
                if(mChronometerState){
                    stopChronometer();
                } else{
                    startChronometer();
                }
           });

        /*Оброботка нажатия на кнопку начать и остановить запись голоса*/
        mStartRecordingButton.setOnClickListener(v -> {
                if (mViewModel.isRecording.getValue()){
                    stopRecording();
                }else {
                    if(new Permissions().checkRecordPermission(getActivity())){
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

        /*Завершение упражнения при нажатии стрелки назад*/
        mButtonFinish.setOnClickListener(v->{
            Navigation.findNavController(view).popBackStack();
        });
    }

    private void stopChronometer() {
        mButtonStartPause.setImageResource(R.drawable.ic_play_arrow50dp);
        mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer_allTime.getBase();
        mWorldTimePauseOffSet = SystemClock.elapsedRealtime() - mChronometer_1worldTime.getBase();
        mChronometer_allTime.stop();
        mChronometer_1worldTime.stop();
        mButtonNextWorld.setVisibility(View.INVISIBLE);
        mButtonFinish.setVisibility(View.VISIBLE);
        mChronometerState = false;
    }

    private void startChronometer() {
        mButtonStartPause.setImageResource(R.drawable.ic_pause);
        mChronometer_allTime.setBase(SystemClock.elapsedRealtime() - mPauseOffSet);
        mChronometer_allTime.start();
        mChronometer_1worldTime.setBase(SystemClock.elapsedRealtime() - mWorldTimePauseOffSet);
        mChronometer_1worldTime.start();
        mButtonFinish.setVisibility(View.INVISIBLE);
        mButtonNextWorld.setVisibility(View.VISIBLE);
        mChronometerState = true;
    }

    private void getWordsByExId() {
        switch (mIdEx){
            case 1:
                mArrayWorlds_ex1 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayTextUnderThumb = getResources().getStringArray(R.array.TextUnderThumb_ex1);
                mMaterialToolbar.setTitle("Лингвистические пирамиды");
                mThumbAndText.setVisibility(View.VISIBLE);
                mShort_des.setText("Обобщайте, разобобщайте, переходите по аналогиям");
                break;
            case 2:
                mArrayWorlds_ex2_living = getResources().getStringArray(R.array.Worlds_items_Alive);
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mMaterialToolbar.setTitle("Чем ворон похож на стол");
                mShort_des.setText("Найдите сходство");
                break;
            case 3:
                mArrayWorlds_ex2_not_living = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayWorlds_ex3_filings = getResources().getStringArray(R.array.Worlds_items_filings);
                mExName = "Чем ворон похож на стул (чувства)";
                mMaterialToolbar.setTitle("Чем ворон похож на стул (чувства)");
                mShort_des.setText("Найдите сходство");
                break;
            case 4:
                mArrayWorlds_ex4 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mMaterialToolbar.setTitle("Продвинутое связывание");
                mShort_des.setText("Найдите сходства");
                break;
            case 5:
                mArrayWorlds_ex5 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mMaterialToolbar.setTitle("О чем вижу, о том и пою");
                mShort_des.setText("Описывайте максимально долго данный предмет");
                break;
            case 6:
                mArrayWorlds_ex6 = getResources().getStringArray(R.array.Worlds_items_abbreviations);
                mMaterialToolbar.setTitle("Другие варианты сокращений");
                mShort_des.setText("Придумайте необычную расшифровку аббревиатуры");
                mButtonNextWorld.setText("Следующая аббревиатура");
                mButtonNextWorld.setTextSize(17);
                break;
            case 7:
                mArrayWorlds_ex7 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mMaterialToolbar.setTitle("Волшебный нейминг");
                mShort_des.setText("Придумайте к этому слову 5 или больше смешных прилагательных");
                break;
            case 8:
                mArrayWorlds_ex8 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mMaterialToolbar.setTitle("Купля - продажа");
                mShort_des.setText("Продайте это");
                break;
            case 9:
                mArrayWorlds_ex9 = getResources().getStringArray(R.array.letters);
                mMaterialToolbar.setTitle("Вспомнить все");
                mShort_des.setText("Назовите 15 слов на эту букву");
                mButtonNextWorld.setText("Следующая буква");
                break;
            case 10:
                mArrayWorlds_ex10 = getResources().getStringArray(R.array.Terms);
                mMaterialToolbar.setTitle("В соавторстве с Далем");
                mShort_des.setText("Дайте определение слову");
                break;
            case 11:
                mArrayWorlds_ex11 = getResources().getStringArray(R.array.Worlds_items_notAlive);
                mMaterialToolbar.setTitle("Тест Роршаха");
                mShort_des.setText("Придумайте чем это может быть еще");
                break;
            case 12:
                mArrayWorlds_ex12 = getResources().getStringArray(R.array.professions);
                mMaterialToolbar.setTitle("Хуже уже не будет");
                mShort_des.setText("Придумайте ситуацию или фразу худшего в мире");
                break;
            case 13:
                mArrayWorlds_ex13 = getResources().getStringArray(R.array.questions);
                mMaterialToolbar.setTitle("Вопрос - ответ");
                mShort_des.setText("Ответьте на вопрос, максимально развернуто");
                mButtonNextWorld.setText("Следующий вопрос");
                break;
            case 14:
                mSetWorlds_ex14.addAll(Set.of((getResources().getStringArray(R.array.Worlds_items_Alive))));
                mSetWorlds_ex14.addAll(Set.of(getResources().getStringArray(R.array.Worlds_items_notAlive)));
                mSetWorlds_ex14.addAll(Set.of(getResources().getStringArray(R.array.professions)));
                mMaterialToolbar.setTitle("Рассказчик - импровизатор");
                mShort_des.setText("Составьте рассказ, используя данные слова");
                mButtonNextWorld.setText("Составте расказ, используя данные слова");
                break;
        }
    }


    /*Остановка записи*/
    private void stopRecording() {
        mStartRecordingButton.setText("Начать запись");
        mViewModel.stopRecording(getContext());
    }

    /*Старт записи*/
    private void startRecording() {
        mStartRecordingButton.setText("Остановить запись");
        mViewModel.startRecording(mExName, requireActivity());
    }

    /*В зависимости от айди упражнения устанавливаем в textView правельное слово или пару слов*/
    @SuppressLint("WrongConstant")
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
            case 13:
                mWorld.setText(mArrayWorlds_ex13[r.nextInt(mArrayWorlds_ex13.length)]);
                mWorld.setTextSize(25);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mWorld.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                }
                break;
//            case 14:
//                Set<String> words = new LinkedHashSet<>();
//
//                while (words.size() == 5){
//                    words.add(mSetWorlds_ex14.[r.nextInt(mSetWorlds_ex14.size())]);
//                }
//
//                for (int i = 0; i < 5; i++){
//                    words[i] =
//                }
//                mWorld.setText(mArrayWorlds_ex14[r.nextInt(mArrayWorlds_ex14.length)]);
//                break;

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

    @Override
    public void onStop() {
            super.onStop();
                if (mViewModel.isRecording.getValue()){
                    stopRecording();
                }
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DateAndTime.insertDataToStatistic(getContext(), mIdEx, mWorldCount, mChronometer_allTime.getBase());
        mChronometer_allTime.stop();
        mChronometer_1worldTime.stop();
    }
}


