package com.YaroslavGorbach.delusionalgenerator.feature.statistics;

import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StatisticsManagerImp implements StatisticsManager {
    private int mNumberWords = 0;
    private long mTimeStart = SystemClock.elapsedRealtime();
    private final List<Integer> mValues = new ArrayList<>();

    @Override
    public void calNumberWords() {
        mNumberWords++;
    }

    @Override
    public void calAverageTime() {
        long timeEnd = SystemClock.elapsedRealtime();
        int seconds = (int) ((timeEnd - mTimeStart)/1000);
        mValues.add(seconds);
        mTimeStart = SystemClock.elapsedRealtime();
    }

    @Override
    public int getAverageTime() {
        return calculateAverage(mValues);
    }

    @Override
    public int getNumberWords() {
        return mNumberWords;
    }

    private int calculateAverage(List <Integer> list) {
        int sum = 0;
        if(!list.isEmpty()) {
            for (int mark : list) {
                sum = sum + mark;
            }
            return sum / list.size();
        }
        return sum;
    }
}
