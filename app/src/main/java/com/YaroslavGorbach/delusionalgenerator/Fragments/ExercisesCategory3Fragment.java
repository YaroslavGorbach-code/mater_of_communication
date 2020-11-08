package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ExercisesCategory3Fragment extends Fragment {

    private TextView mTwisters_tv;
    private ImageButton mNextTwistButton;
    private String[] mTwists = {};
    private Random r = new Random();
    private AdView mAdView;
    private int mExId;
    private long mStartExTime;
    private int mNumber_of_tongue_twisters = 0;

    private static final String ARG_EX_ID = "ARG_EX_ID";




    public ExercisesCategory3Fragment() {
        // Required empty public constructor
    }

    public static ExercisesCategory3Fragment newInstance(int exId) {
        ExercisesCategory3Fragment fragment = new ExercisesCategory3Fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tongue_twister, container, false);
        mTwisters_tv = view.findViewById(R.id.twisters);
        mNextTwistButton = view.findViewById(R.id.nextTwist);

        switch (mExId){
            case 30:
                mTwists = getResources().getStringArray(R.array.twisters_easy);
                break;
            case 31:
                mTwists = getResources().getStringArray(R.array.twisters_easy);
                break;
            case 32:
                mTwists = getResources().getStringArray(R.array.twisters_easy);
                break;
        }

        mStartExTime = SystemClock.elapsedRealtime();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTwist();
        mNextTwistButton.setOnClickListener(v -> {
            setTwist();
        });

        mAdView = view.findViewById(R.id.adViewTab2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void setTwist() {
        YoYo.with(Techniques.FadeIn)
                .duration(400)
                .playOn(mTwisters_tv);
        mTwisters_tv.setText(mTwists[r.nextInt(mTwists.length)]);
        mNumber_of_tongue_twisters++;
    }

    /*Метод используеться для ведения статистики*/
    private void insertMyDateAndTime(){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        //получаем время из секундомера в минутах
        long time = ((SystemClock.elapsedRealtime() - mStartExTime)/1000) / 60;

        Repo.getInstance(getContext()).insertDateAndTime(mExId, date, time);
        Repo.getInstance(getContext()).insertDateAndCountWorlds(mExId, date, mNumber_of_tongue_twisters);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        insertMyDateAndTime();
    }
}