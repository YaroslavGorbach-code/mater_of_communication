package com.YaroslavGorbach.delusionalgenerator.feature.timer;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TimerImp implements Timer{
    private final long ONE_SECOND = 1000L;
    private final long COUNTDOWN_TIME = 60000L;

    private final MutableLiveData<String> mTimerValue = new MutableLiveData<>("0");
    private final MutableLiveData<Boolean> mFinishEvent = new MutableLiveData<>(false);


    @Override
    public void start() {
        new CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
               mTimerValue.setValue(String.valueOf(millisUntilFinished / ONE_SECOND));
            }

            @Override
            public void onFinish() {
                mTimerValue.setValue("0");
                mFinishEvent.setValue(true);
            }
        }.start();
    }

    @Override
    public LiveData<Boolean> onFinish() {
        return mFinishEvent;
    }

    @Override
    public LiveData<String> getValue() {
        return mTimerValue;
    }
}
