package com.YaroslavGorbach.delusionalgenerator.screen.exercise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
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

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.util.Statistics;
import com.YaroslavGorbach.delusionalgenerator.util.Permissions;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ExercisesCategory1Fragment extends Fragment {
    private ImageButton mButtonStartPause;
    private Chronometer mChronometer_allTime;
    private Chronometer mChronometer_1worldTime;
    private long mPauseOffSet = 0;
    private long mWorldTimePauseOffSet = 0;
    private Button mButtonNextWorld;
    private TextView mWorld;
    private Button mButtonFinish;
    private int mIdEx;
    private ImageView mThumb;
    private TextView mTextUnderThumb;
    private TextView mShort_des;
    private RelativeLayout mThumbAndText;
    private TextView mWorldCounter;
    private int mWorldCount;
    private Button mStartRecordingButton;
    private String mExName;
    private LiveData<Exercise> mEx;
    private ExerciseCategory1ViewModel mViewModel;
    private boolean mChronometerState;


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
        mThumb = view.findViewById(R.id.thumb_iv);
        mTextUnderThumb = view.findViewById(R.id.textUnderThumb);
        mShort_des = view.findViewById(R.id.description_short);
        mChronometer_1worldTime = view.findViewById(R.id.chronometer_1world_time);
        mThumbAndText = view.findViewById(R.id.thumbAndText);
        mWorldCounter = view.findViewById(R.id.world_counter);
        mStartRecordingButton = view.findViewById(R.id.buttonStartRecording);
        mIdEx = ExercisesCategory1FragmentArgs.fromBundle(requireArguments()).getIdEx();
        mViewModel = new ViewModelProvider(this, new ExerciseCategory1ViewModelFactory(
                requireActivity().getApplication(), mIdEx)).get(ExerciseCategory1ViewModel.class);
        mEx = mViewModel.getExerciseById(mIdEx);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //установка максимального зщначения для счетчика слов
        mWorldCounter.setText(String.format("%s/%s", mWorldCount, mViewModel.getMaxWorldCount(mIdEx)));

        /*запуск секундомера*/
        startChronometer();

        /*устанавливаем подходящий текст в тулбар и на кнопку и в описание, устанавливаем слово*/
        mViewModel.exShortDesc.observe(getViewLifecycleOwner(), shortDesc-> mShort_des.setText(shortDesc));
        mViewModel.buttonNextText.observe(getViewLifecycleOwner(), buttonNextText-> mButtonNextWorld.setText(buttonNextText));
        changeWord();

        /*показ банера*/
        AdMob.showBanner(view.findViewById(R.id.banner));

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
            changeWord();
            mChronometer_1worldTime.setBase(SystemClock.elapsedRealtime());
            mWorldCount++;
            mWorldCounter.setText(String.format("%s/%s", mWorldCount, mViewModel.getMaxWorldCount(mIdEx)));
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
    private void changeWord(){
        YoYo.with(Techniques.FadeIn)
                .duration(400)
                .playOn(mWorld);
        mWorld.setText(mViewModel.getWord());
        switch (mIdEx) {
            case 1:
                setThumbAndText();
                animateThumb();
                break;
            case 6:
                mButtonNextWorld.setTextSize(17);
                break;
            case 13:
                mWorld.setTextSize(25);
                break;
            case 14:
                mWorld.setTextSize(30);
                break;
        }
    }

    /*Уставнока значения борльшого пальца*/
    private void setThumbAndText(){
        mThumbAndText.setVisibility(View.VISIBLE);
        mTextUnderThumb.setText(mViewModel.getThumbWord());
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
        Statistics.insertDataToStatistics(getContext(), mIdEx, mWorldCount, mChronometer_allTime.getBase());
        mChronometer_allTime.stop();
        mChronometer_1worldTime.stop();
    }
}


