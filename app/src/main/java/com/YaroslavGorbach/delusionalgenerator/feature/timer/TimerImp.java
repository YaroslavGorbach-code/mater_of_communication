package com.YaroslavGorbach.delusionalgenerator.feature.timer;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TimerImp implements Timer{
    private final long DONE = 0L;
    private final long ONE_SECOND = 1000L;
    private final long COUNTDOWN_TIME = 60000L;

    private final MutableLiveData<Long> mTimerValue = new MutableLiveData<>(0L);
    private final MutableLiveData<Boolean> mFinishEvent = new MutableLiveData<>(false);


    @Override
    public void start() {
        new CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerValue.setValue(millisUntilFinished / ONE_SECOND);
            }

            @Override
            public void onFinish() {
                mTimerValue.setValue(DONE);
                mFinishEvent.setValue(true);
            }
        }.start();
    }

    @Override
    public LiveData<Boolean> onFinish() {
        return mFinishEvent;
    }

    @Override
    public LiveData<Long> getValue() {
        return mTimerValue;
    }
}
