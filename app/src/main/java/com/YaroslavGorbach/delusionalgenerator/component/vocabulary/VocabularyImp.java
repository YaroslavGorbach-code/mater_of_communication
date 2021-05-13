package com.YaroslavGorbach.delusionalgenerator.component.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Statistics;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

import java.util.Date;

public class VocabularyImp implements Vocabulary {
    private final MutableLiveData<Integer> mClickCount = new MutableLiveData<>(0);

    private final Timer mTimer;
    private final Exercise mExercise;
    private final StatisticsManager mStatisticsManager;
    private final Repo mRepo;

    public VocabularyImp(Exercise.Name name, Exercise.Type type, Timer timer, StatisticsManager statisticsManager, Repo repo){
        mTimer = timer;
        mRepo = repo;
        mExercise = mRepo.getExercise(name);
        mExercise.type = type;
        mStatisticsManager = statisticsManager;
        mTimer.start();
        if (mExercise.type == Exercise.Type.DAILY) {
            mExercise.done = repo.getTrainingExDone(mExercise);
            mExercise.aim = repo.getTrainingExAim(mExercise);
        }
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
        if (mExercise.type == Exercise.Type.DAILY){
            mExercise.done ++;
            mRepo.updateTrainingEx(mExercise);
        }
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
