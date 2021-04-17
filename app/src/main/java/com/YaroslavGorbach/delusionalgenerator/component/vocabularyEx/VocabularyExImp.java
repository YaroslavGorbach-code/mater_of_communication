package com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.TimerImp;
import com.YaroslavGorbach.delusionalgenerator.util.Permissions;

public class VocabularyExImp implements VocabularyEx{
    private final MutableLiveData<Integer> clickCount = new MutableLiveData<>(0);

    private final Timer mTimer = new TimerImp();
    private final ExModel mExModel;

    public VocabularyExImp(ExModel exModel){
        mExModel = exModel;
        mTimer.start();
    }

    @Override
    public void onClick() {
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
