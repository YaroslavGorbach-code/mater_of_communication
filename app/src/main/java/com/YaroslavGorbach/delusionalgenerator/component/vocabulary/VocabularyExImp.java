package com.YaroslavGorbach.delusionalgenerator.component.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.TimerImp;

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
    public String getShortDesc() {
        return mExModel.shortDesc[0];
    }

    @Override
    public LiveData<Integer> getClickCount() {
        return clickCount;
    }

    @Override
    public LiveData<Long> getTimerValue() {
        return mTimer.getValue();
    }

}
