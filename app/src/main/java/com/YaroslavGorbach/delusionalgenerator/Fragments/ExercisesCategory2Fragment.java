package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Helpers.DateAndTime;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.MaterialToolbar;


public class ExercisesCategory2Fragment extends Fragment {

    private int mExId;
    private long mTime_in_milli_seconds = 0L;
    private int mWordCountValue = 0;
    private boolean mIsRunning = false;
    private TextView mWordCounter_tv;
    private TextView mTimer_tv;
    private TextView mWhatTodo_tv;
    private ConstraintLayout mClickAria;
    private TextView mRecord_tv;

    private MaterialToolbar mMaterialToolbar;


    public ExercisesCategory2Fragment() {
        // Required empty public constructor
    }

    public static ExercisesCategory2Fragment newInstance() {
        return new ExercisesCategory2Fragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category_2, container, false);

        /*поиск всех вью и старт таймера*/
        mWordCounter_tv = view.findViewById(R.id.worldCounterText);
        mTimer_tv = view.findViewById(R.id.timer);
        mExId = ExercisesCategory2FragmentArgs.fromBundle(getArguments()).getIdEx();
        mMaterialToolbar = view.findViewById(R.id.toolbar_ex_category_2);
        mMaterialToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mWhatTodo_tv = view.findViewById(R.id.whatToDo);
        mClickAria = view.findViewById(R.id.clickAria);
        mRecord_tv = view.findViewById(R.id.worldRecordText);
        startTimer();
        mIsRunning = true;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*показ банера*/
        AdView mAdView = view.findViewById(R.id.adViewTabEx2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*завершить упражнение при клике на стрелку назад*/
        mMaterialToolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        setExDescription();

        mClickAria.setOnClickListener(v -> {
            if (mIsRunning){
                mWordCountValue +=1;
                mWordCounter_tv.setText("Названо слов: " + mWordCountValue);
            }
        });
    }

    private void setExDescription() {
        switch (mExId){
        case 20:
        mMaterialToolbar.setTitle("Существительные");
        mWhatTodo_tv.setText("Нозовите как можно больше существительных(Кто? Что?). После каждого названого слова, нажмите" +
                " на экран, для учета результата");
        break;
        case 21:
        mMaterialToolbar.setTitle("Прилагательные");
            mWhatTodo_tv.setText("Нозовите как можно больше прилагательных(Какой? Какое?). После каждого названого слова, нажмите" +
                    " на экран, для учета результата");
        break;
        case 22:
        mMaterialToolbar.setTitle("Глаголы");
            mWhatTodo_tv.setText("Нозовите как можно больше глаголов(Что делать? Что сделать?). После каждого названого слова, нажмите" +
                    " на экран, для учета результата");

        break;
    }
    }

    private void startTimer() {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTime_in_milli_seconds = millisUntilFinished;
                updateTextUI();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                //loadConfeti()
                mIsRunning = false;
                if (Repo.getInstance(getContext()).getMaxWorldCount(mExId)!=0){
                    mRecord_tv.setVisibility(View.VISIBLE);
                    mRecord_tv.setText("Рекорд: " + Repo.getInstance(getContext()).getMaxWorldCount(mExId));
                }
            }
        }.start();


    }

    @SuppressLint("SetTextI18n")
    private void updateTextUI () {
        long minute = (mTime_in_milli_seconds / 1000) / 60;
        long seconds = (mTime_in_milli_seconds / 1000) % 60;
            mTimer_tv.setText(minute + ":" + seconds);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        DateAndTime.insertDataToStatistic(getContext(), mExId, mWordCountValue, 10);
    }
}