package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;

public class ExerciseCategory2ViewModel extends AndroidViewModel {

    // This is when the game is over
    private final long DONE = 0L;

    // This is the number of milliseconds in a second
    private final long ONE_SECOND = 1000L;

    // This is the total time
    private final long COUNTDOWN_TIME = 60000L;

    private final MutableLiveData<Long> _currentTime = new MutableLiveData<>();

    // The String version of the current time
    public LiveData<String> currentTimeString =
            Transformations.map(_currentTime, DateUtils::formatElapsedTime);

    // Event which triggers the end of the game
    private final MutableLiveData<Boolean> _eventGameFinish = new MutableLiveData<>(false);
    public LiveData<Boolean> eventGameFinish = _eventGameFinish;

    private final MutableLiveData<Integer> _countValue = new MutableLiveData<>(0);
    public LiveData<Integer> countValue = _countValue;


    public ExerciseCategory2ViewModel(@NonNull Application application) {
        super(application);
        startTimer();
    }

    private void startTimer() {
        new CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                _currentTime.setValue(millisUntilFinished / ONE_SECOND);
            }

            @Override
            public void onFinish() {
                _currentTime.setValue(DONE);
                _eventGameFinish.setValue(true);
            }
        }.start();
    }

    public void valuePlus(){
        _countValue.setValue((_countValue.getValue()) + 1);
    }

}
