package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.R;

import java.util.Random;

public class ExerciseCategory3ViewModel extends AndroidViewModel {

    private String[] mTwists = {};
    private final Random r = new Random();


    public ExerciseCategory3ViewModel(@NonNull Application application, int exId) {
        super(application);

        switch (exId) {
            case 30:
                mTwists = application.getResources().getStringArray(R.array.twisters_easy);
                break;
            case 31:
                mTwists = application.getResources().getStringArray(R.array.twisters_hard);
                break;
            case 32:
                mTwists = application.getResources().getStringArray(R.array.twisters_very_hard);
                break;
        }
    }

    private final MutableLiveData<Integer> _clickCounter = new MutableLiveData<>(0);
    public LiveData<Integer> clickCounter = _clickCounter;

    private final MutableLiveData<Integer> _numberOfTwisters = new MutableLiveData<>(1);
    public LiveData<Integer> numberOfTwisters = _numberOfTwisters;

    public void nextClick(){
        if (_clickCounter.getValue() !=5){
            _clickCounter.setValue(_clickCounter.getValue() + 1);
        }else {
            _clickCounter.setValue(1);
            _numberOfTwisters.setValue(numberOfTwisters.getValue() + 1);
        }
    }

    public String getTwist(){
        if (mTwists!=null){
            return mTwists[r.nextInt(mTwists.length)];
        }
        throw new IllegalArgumentException("Array is empty");
    }
}
