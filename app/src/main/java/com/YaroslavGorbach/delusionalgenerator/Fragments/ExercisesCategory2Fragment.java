package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesCategory2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesCategory2Fragment extends Fragment {


    private static final String ARG_EX_ID = "ARG_EX_ID";

    private int mExId;
    private final long START_MILLI_SECONDS = 60000L;
    private CountDownTimer mContDownTimer;
    private long mTime_in_milli_seconds = 0L;
    private int mWordCountValue = 0;
    private boolean mIsRunning = false;
    private TextView mWordCounter_tv;
    private TextView mTimer_tv;



    public ExercisesCategory2Fragment() {
        // Required empty public constructor
    }

    public static ExercisesCategory2Fragment newInstance(int exId) {
        ExercisesCategory2Fragment fragment = new ExercisesCategory2Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EX_ID, exId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExId = getArguments().getInt(ARG_EX_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category2, container, false);
        mWordCounter_tv = view.findViewById(R.id.worldCounterText);
        mTimer_tv = view.findViewById(R.id.timer);



            //При клике на кнопку начать скрить кнопку и описание упражнения и показать само упражнение

                startTimer(START_MILLI_SECONDS);
                mIsRunning = true;

            view.setOnClickListener(v -> {
                if (mIsRunning){
                    mWordCountValue +=1;
                    mWordCounter_tv.setText("Названо слов: " + mWordCountValue);
                }
            });

        return view;
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
        Repo.getInstance(getContext()).insertDateAndCountWorlds(mExId, date, mWordCountValue);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        insertMyDateAndTime();
    }
}