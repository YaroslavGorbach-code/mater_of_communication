package com.YaroslavGorbach.delusionalgenerator.component.tonguetwistersEx;

import android.content.res.Resources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.WordType;

import java.util.List;
import java.util.Random;

public class TongueTwisterExImp implements TongueTwisterEx {
    private final MutableLiveData<String> _tongueTwister = new MutableLiveData<>("test");
    private final MutableLiveData<String> _shortDesc = new MutableLiveData<>("test");

    private final ExModel mExModel;
    private final Repo mRepo;
    private final Resources mResources;
    private final Random mRandom = new Random();
    private int clickCount = 0;

    public TongueTwisterExImp(ExModel exModel, Repo repo, Resources resources){
        mExModel = exModel;
        mRepo = repo;
        mResources = resources;
        // set tt and desc immediately
        setTt();
        setShortDesc();
    }

    private void setTt() {
        switch (mExModel.name) {
            case EASY_TONGUE_TWISTERS:
                List<String> words = mRepo.getWords(WordType.EASY_T_T, mResources);
                _tongueTwister.postValue(words.get(mRandom.nextInt(words.size())));
                break;
        }
    }

    private void setShortDesc() {
        _shortDesc.postValue(mExModel.shortDesc[clickCount]);
    }

    @Override
    public void onNextClick() {
        clickCount ++;
        if (clickCount < mExModel.shortDesc.length){
            setShortDesc();
        }
        if (clickCount >= mExModel.shortDesc.length){
            clickCount = 0;
            setTt();
            setShortDesc();
        }
    }

    @Override
    public LiveData<String> getTongueTwister() {
        return _tongueTwister;
    }

    @Override
    public LiveData<String> getShortDesc() {
        return _shortDesc;
    }
}
