package com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.Statistics;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

import java.util.Date;

public class VocabularyExImp implements VocabularyEx{
    private final MutableLiveData<Integer> clickCount = new MutableLiveData<>(0);

    private final Timer mTimer;
    private final ExModel mExModel;
    private final StatisticsManager mStatisticsManager;
    private final Repo mRepo;

    public VocabularyExImp(ExModel exModel, Timer timer, StatisticsManager statisticsManager, Repo repo){
        mExModel = exModel;
        mTimer = timer;
        mStatisticsManager = statisticsManager;
        mRepo = repo;
        mTimer.start();
    }

    @Override
    public void onClick() {
        mStatisticsManager.calNumberWords();
        clickCount.postValue(clickCount.getValue()+1);
    }

    @Override
    public int getShortDescId() {
        return mExModel.shortDescIds[0];
    }

    @Override
    public Result getResultState() {
        Result result;
        if (clickCount.getValue()<25){
            result = Result.BAD;
            result.setNumber(clickCount.getValue());
            return result;
        }
        if (clickCount.getValue()>25 && clickCount.getValue()<40){
            result = Result.GOOD;
            result.setNumber(clickCount.getValue());
            return result;
        }
        if (clickCount.getValue()>40){
            result = Result.VERY_GOOD;
            result.setNumber(clickCount.getValue());
            return result;
        }
        return Result.GOOD;
    }

    @Override
    public void saveStatistics() {
        // we ned to add plus one word
        mRepo.addStatistics(new Statistics(mExModel.getId(),
                mStatisticsManager.getNumberWords(), new Date().getTime()));
    }

    @Override
    public LiveData<Integer> getClickCount() {
        return clickCount;
    }

    @Override
    public LiveData<Long> getTimerValue() {
        return mTimer.getValue();
    }

    @Override
    public LiveData<Boolean> onTimerFinish() {
        return mTimer.onFinish();
    }

}
