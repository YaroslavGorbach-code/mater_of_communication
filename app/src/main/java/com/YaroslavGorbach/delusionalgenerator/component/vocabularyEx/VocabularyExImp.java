package com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.Statistics;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

import java.util.Date;

public class VocabularyExImp implements VocabularyEx{
    private final MutableLiveData<Integer> mClickCount = new MutableLiveData<>(0);

    private final Timer mTimer;
    private final Exercise mExercise;
    private final StatisticsManager mStatisticsManager;
    private final Repo mRepo;

    public VocabularyExImp(Exercise.Name name, Timer timer, StatisticsManager statisticsManager, Repo repo){
        mTimer = timer;
        mStatisticsManager = statisticsManager;
        mRepo = repo;
        mExercise = mRepo.getExercise(name);
        mTimer.start();
    }

    @Override
    public void onClick() {
        mStatisticsManager.calNumberWords();
        mClickCount.postValue(mClickCount.getValue()+1);
    }

    @Override
    public int getShortDescId() {
        return mExercise.getShortDescIds()[0];
    }

    @Override
    public Result getResultState() {
        Result result;
        if (mClickCount.getValue()<25){
            result = Result.BAD;
            result.setNumberWords(mClickCount.getValue());
            return result;
        }
        if (mClickCount.getValue()>25 && mClickCount.getValue()<40){
            result = Result.GOOD;
            result.setNumberWords(mClickCount.getValue());
            return result;
        }
        if (mClickCount.getValue()>40){
            result = Result.VERY_GOOD;
            result.setNumberWords(mClickCount.getValue());
            return result;
        }
        return Result.GOOD;
    }

    @Override
    public void saveStatistics() {
        // we ned to add plus one word
        mRepo.addStatistics(new Statistics(mExercise.getName(),
                mStatisticsManager.getNumberWords(), new Date().getTime()));
    }

    @Override
    public LiveData<Integer> getClickCount() {
        return mClickCount;
    }


    @Override
    public LiveData<String> getTimerValue() {
        return mTimer.getValue();
    }

    @Override
    public LiveData<Boolean> onTimerFinish() {
        return mTimer.onFinish();
    }

}
