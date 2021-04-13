package com.YaroslavGorbach.delusionalgenerator.feature.chronometer;

import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.Chronometer;

public class ChronometerImp implements Chronometer {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

//    private void stopChronometer() {
//        mButtonStartPause.setImageResource(R.drawable.ic_play_arrow50dp);
//        mPauseOffSet = SystemClock.elapsedRealtime() - mChronometer_allTime.getBase();
//        mWorldTimePauseOffSet = SystemClock.elapsedRealtime() - mChronometer_1worldTime.getBase();
//        mChronometer_allTime.stop();
//        mChronometer_1worldTime.stop();
//        mButtonNextWorld.setVisibility(View.INVISIBLE);
//        mButtonFinish.setVisibility(View.VISIBLE);
//        mChronometerState = false;
//    }
//
//    private void startChronometer() {
//        mButtonStartPause.setImageResource(R.drawable.ic_pause);
//        mChronometer_allTime.setBase(SystemClock.elapsedRealtime() - mPauseOffSet);
//        mChronometer_allTime.start();
//        mChronometer_1worldTime.setBase(SystemClock.elapsedRealtime() - mWorldTimePauseOffSet);
//        mChronometer_1worldTime.start();
//        mButtonFinish.setVisibility(View.INVISIBLE);
//        mButtonNextWorld.setVisibility(View.VISIBLE);
//        mChronometerState = true;
//    }

}
