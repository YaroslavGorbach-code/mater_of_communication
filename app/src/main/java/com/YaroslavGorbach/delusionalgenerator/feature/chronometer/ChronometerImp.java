package com.YaroslavGorbach.delusionalgenerator.feature.chronometer;

import android.os.SystemClock;


public class ChronometerImp implements Chronometer {
    private long mPauseOffSet = 0;
    private boolean mIsRunning = false;
    private final android.widget.Chronometer mChronometer;

    public ChronometerImp(android.widget.Chronometer chronometer){
        mChronometer = chronometer;
        start();
    }

    @Override
    public void start() {
        mChronometer.setBase(SystemClock.elapsedRealtime() - mPauseOffSet);
        mChronometer.start();
        mIsRunning = true;
    }

    @Override
    public void pause() {
        mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer.getBase();
        mChronometer.stop();
        mIsRunning = false;
    }

    @Override
    public void reset() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
    }

    @Override
    public boolean getState() {
        return mIsRunning;
    }

}
