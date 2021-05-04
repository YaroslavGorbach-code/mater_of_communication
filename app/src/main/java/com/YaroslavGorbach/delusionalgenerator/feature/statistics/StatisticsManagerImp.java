package com.YaroslavGorbach.delusionalgenerator.feature.statistics;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class StatisticsManagerImp implements StatisticsManager {
    private int mNumberWords = -1;
    private long mTimeStart = 1;
    private final List<Long> mValues = new ArrayList<>();

    @Override
    public void calNumberWords() {
        mNumberWords++;
    }

    @Override
    public void calAverageTime() {
        long timeEnd = SystemClock.elapsedRealtime();
        mValues.add(timeEnd - mTimeStart);
        mTimeStart = SystemClock.elapsedRealtime();
    }

    @Override
    public int getAverageTime() {
        return calculateAverage(mValues)/1000;
    }

    @Override
    public int getNumberWords() {
        return mNumberWords;
    }

    private int calculateAverage(List <Long> list) {
        Long sum = 0L;
        if(!list.isEmpty()) {
            for (Long mark : list) {
                sum += mark;
            }
            return sum.intValue() / list.size();
        }
        return sum.intValue();
    }
}
