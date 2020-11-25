package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ExercisesCategory2Fragment extends Fragment {

    private int mExId;
    private final long START_MILLI_SECONDS = 60000L;
    private long mTime_in_milli_seconds = 0L;
    private int mWordCountValue = 0;
    private boolean mIsRunning = false;
    private TextView mWordCounter_tv;
    private TextView mTimer_tv;
    private MaterialToolbar mMaterialToolbar;


    public ExercisesCategory2Fragment() {
        // Required empty public constructor
    }

    public static ExercisesCategory2Fragment newInstance(int exId) {
        return new ExercisesCategory2Fragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category_2, container, false);
        mWordCounter_tv = view.findViewById(R.id.worldCounterText);
        mTimer_tv = view.findViewById(R.id.timer);
        mExId = ExercisesCategory2FragmentArgs.fromBundle(getArguments()).getIdEx();
        mMaterialToolbar = view.findViewById(R.id.toolbar_ex_category_2);
        mMaterialToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        startTimer(START_MILLI_SECONDS);
        mIsRunning = true;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMaterialToolbar.setNavigationOnClickListener(v -> {
            NavDirections action = ExercisesCategory2FragmentDirections.
                    actionExercisesCategory2FragmentToExercisesFragmentV2();
            Navigation.findNavController(view).navigate(action);
        });

        switch (mExId){
        case 20:
        mMaterialToolbar.setTitle("Существительные");
        break;
        case 21:
        mMaterialToolbar.setTitle("Прилагательные");
        break;
        case 22:
        mMaterialToolbar.setTitle("Глаголы");
        break;
    }

        view.setOnClickListener(v -> {
            if (mIsRunning){
                mWordCountValue +=1;
                mWordCounter_tv.setText("Названо слов: " + mWordCountValue);
            }
        });
    }

    private void startTimer(long time_in_seconds) {
        new CountDownTimer(time_in_seconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTime_in_milli_seconds = millisUntilFinished;
                updateTextUI();

            }

            @Override
            public void onFinish() {
                //loadConfeti()
                mIsRunning = false;
                insertMyDateAndTime();
            }
        }.start();

    }

    @SuppressLint("SetTextI18n")
    private void updateTextUI () {
        long minute = (mTime_in_milli_seconds / 1000) / 60;
        long seconds = (mTime_in_milli_seconds / 1000) % 60;
            mTimer_tv.setText(minute + ":" + seconds);
    }

    /*Метод используеться для ведения статистики*/
    private void insertMyDateAndTime(){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        long time = 10;


        Repo.getInstance(getContext()).insertDateAndTime(mExId, date, time);
        Repo.getInstance(getContext()).insertDateAndCountWorlds(mExId, date, mWordCountValue);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        insertMyDateAndTime();
    }
}