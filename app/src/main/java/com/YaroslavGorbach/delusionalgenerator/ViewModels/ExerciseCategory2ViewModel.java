package com.YaroslavGorbach.delusionalgenerator.ViewModels;

import android.app.Application;
import android.os.CountDownTimer;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.YaroslavGorbach.delusionalgenerator.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ExerciseCategory2ViewModel extends AndroidViewModel {

    private final Random r = new Random();

    // This is when the game is over
    private final long DONE = 0L;

    // This is the number of milliseconds in a second
    private final long ONE_SECOND = 1000L;

    // This is the total time
    private final long COUNTDOWN_TIME = 60000L;

    private final List<String> resoldBadList;
    private final List<String> resoldGoodList;

    private final MutableLiveData<Long> _currentTime = new MutableLiveData<>();

    // The String version of the current time
    public LiveData<String> currentTimeString =
            Transformations.map(_currentTime, DateUtils::formatElapsedTime);

    // Event which triggers the end of the game
    private final MutableLiveData<Boolean> _eventGameFinish = new MutableLiveData<>(false);
    public LiveData<Boolean> eventGameFinish = _eventGameFinish;

    private final MutableLiveData<Integer> _countValue = new MutableLiveData<>(0);
    public LiveData<Integer> countValue = _countValue;

    private final MutableLiveData<String> _exName = new MutableLiveData<>();
    public LiveData<String> exName = _exName;

    private final MutableLiveData<String> _nextExName = new MutableLiveData<>();
    public LiveData<String> nextExName = _nextExName;

    private final MutableLiveData<String> _exShortDescription = new MutableLiveData<>();
    public LiveData<String> exShortDescription = _exShortDescription;

    private final MutableLiveData<Integer> _exNormWords = new MutableLiveData<>(0);
    public LiveData<Integer> exNormWords = _exNormWords;


    public ExerciseCategory2ViewModel(@NonNull Application application, int exId) {
        super(application);
        startTimer();
        resoldBadList = Arrays.asList(application.getResources().getStringArray(R.array.resultBad));
        resoldGoodList = Arrays.asList(application.getResources().getStringArray(R.array.resultGood));
        switch (exId){
            case 20:
                _exName.setValue("Существительные");
                _exShortDescription.setValue("Назовите как можно больше существительных(Кто? Что?). " +
                        "После каждого названого слова, нажмите" +
                        " на экран, для учета результата");
                _exNormWords.setValue(54);
                _nextExName.setValue("Прилагательные");
                break;
            case 21:
                _exName.setValue("Прилагательные");
                _exShortDescription.setValue("Назовите как можно больше прилагательных(Какой? Какое?)." +
                        " После каждого названого слова, нажмите" +
                        " на экран, для учета результата");
                _exNormWords.setValue(46);
                _nextExName.setValue("Глаголы");

                break;
            case 22:
                _exName.setValue("Глаголы");
                _exShortDescription.setValue("Назовите как можно больше глаголов(Что делать? Что сделать?). После каждого названого слова, нажмите" +
                        " на экран, для учета результата");
                _exNormWords.setValue(42);
                _nextExName.setValue("Существительные");
                break;
        }
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

    public String getBadResoldString(){
        return resoldBadList.get(r.nextInt(resoldBadList.size()));
    }

    public String getGoodResoldString(){
        return resoldGoodList.get(r.nextInt(resoldBadList.size()));
    }

}
