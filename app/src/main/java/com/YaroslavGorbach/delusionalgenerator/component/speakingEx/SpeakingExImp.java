package com.YaroslavGorbach.delusionalgenerator.component.speakingEx;

import android.content.Context;
import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.Statistics;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.Chronometer;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorder;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class SpeakingExImp implements SpeakingEx {
    private final MutableLiveData<String> _word = new MutableLiveData<>("null");
    private final MutableLiveData<Integer> _shortDesc = new MutableLiveData<>(R.string.short_desc_tt_1);
    private final MutableLiveData<Boolean> mIsRecording = new MutableLiveData<>();
    private final Chronometer mChronometer;
    private final Chronometer mChronometerOneWord;
    private final VoiceRecorder mVoiceRecorder;
    private final StatisticsManager mStatisticsManager;

    private final ExModel mExModel;
    private final Repo mRepo;
    private final Resources mResources;
    private final Random mRandom = new Random();
    private int mClickCount = 0;

    public SpeakingExImp(
            ExModel exModel,
            Repo repo,
            StatisticsManager statisticsManager,
            Resources resources,
            Chronometer chronometer,
            Chronometer chronometerOneWord,
            VoiceRecorder voiceRecorder
    ){
        mExModel = exModel;
        mRepo = repo;
        mStatisticsManager = statisticsManager;
        mResources = resources;
        mChronometer = chronometer;
        mChronometerOneWord = chronometerOneWord;
        mVoiceRecorder = voiceRecorder;

        // init immediately
        setShortDesc();
        setWord();
    }

    @Override
    public void onNext() {
        if (mExModel.category == ExModel.Category.TONGUE_TWISTER){
            mClickCount++;
            if (mClickCount < mExModel.shortDescIds.length){
                setShortDesc();
            }
            if (mClickCount >= mExModel.shortDescIds.length){
                mClickCount = 0;
                setShortDesc();
                setWord();
            }
        }else {
            setWord();
        }
    }

    @Override
    public LiveData<Integer> getShortDescId() {
        return _shortDesc;
    }

    @Override
    public LiveData<String> getWord() {
        return _word;
    }

    @Override
    public String getExName() {
        return mExModel.name.getName();
    }

    @Override
    public void startPauseChronometer() {
        if (mChronometer.getState() && mChronometerOneWord.getState()){
            mChronometer.pause();
            mChronometerOneWord.pause();
        }else {
            mChronometer.start();
            mChronometerOneWord.start();
        }
    }

    @Override
    public void saveStatistics() {
        if (mExModel.category == ExModel.Category.SPEAKING){
            mRepo.addStatistics(new Statistics(
                    mExModel.getId(), mStatisticsManager.getNumberWords(), new Date().getTime()));
        }else {
            mRepo.addStatistics(new Statistics(
                    mExModel.getId(), mStatisticsManager.getAverageTime(), new Date().getTime()));
        }
    }

    @Override
    public void startStopRecord(Context context) {
        if (mVoiceRecorder.getState()){
            mVoiceRecorder.stop();
            mIsRecording.postValue(false);
        }else {
            mVoiceRecorder.start(context, mExModel.name.getName());
            mIsRecording.postValue(true);
        }
    }

    @Override
    public LiveData<Boolean> getRecordingState() {
        return mIsRecording;
    }

    private void setWord(){
        mStatisticsManager.calNumberWords();
        mStatisticsManager.calAverageTime();
        mChronometerOneWord.reset();
        List<String> words;
        switch (mExModel.name){
            case LINGUISTIC_PYRAMIDS:
                words = mRepo.getWords(Repo.WordType.NOT_ALIVE, mResources);
                _word.postValue(words.get(mRandom.nextInt(words.size())));
                setShortDesc();
                break;
            case EASY_TONGUE_TWISTERS:
                words = mRepo.getWords(Repo.WordType.EASY_T_T, mResources);
                _word.postValue(words.get(mRandom.nextInt(words.size())));
                break;
        }
    }

    private void setShortDesc() {
        if (mExModel.category == ExModel.Category.TONGUE_TWISTER) {
            _shortDesc.postValue(mExModel.shortDescIds[mClickCount]);
        }else {
            _shortDesc.postValue(mExModel.shortDescIds[mRandom.nextInt(mExModel.shortDescIds.length)]);
        }
    }
}
