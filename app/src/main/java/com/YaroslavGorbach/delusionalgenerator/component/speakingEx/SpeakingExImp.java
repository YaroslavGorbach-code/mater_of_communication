package com.YaroslavGorbach.delusionalgenerator.component.speakingEx;

import android.content.Context;
import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.WordType;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.Chronometer;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.ChronometerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorder;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;

import java.util.List;
import java.util.Random;

public class SpeakingExImp implements SpeakingEx {
    private final MutableLiveData<String> _word = new MutableLiveData<>("test");
    private final MutableLiveData<Integer> _shortDesc = new MutableLiveData<>(R.string.short_desc_tt_1);
    private final MutableLiveData<Boolean> mIsRecording = new MutableLiveData<>();
    private final Chronometer mChronometer;
    private final Chronometer mChronometerOneWord;
    private final VoiceRecorder mVoiceRecorder;

    private final ExModel mExModel;
    private final Repo mRepo;
    private final Resources mResources;
    private final Random mRandom = new Random();
    private int clickCount = 0;


    public SpeakingExImp(
            ExModel exModel,
            Repo repo,
            Resources resources,
            android.widget.Chronometer chronometer,
            android.widget.Chronometer chronometerOneWord
    ){
        mExModel = exModel;
        mRepo = repo;
        mResources = resources;
        mChronometer = new ChronometerImp(chronometer);
        mChronometerOneWord = new ChronometerImp(chronometerOneWord);
        mVoiceRecorder = new VoiceRecorderImp();

        // init immediately
        setShortDesc();
        setWord();
    }

    @Override
    public void onNext() {
        if (mExModel.category == ExModel.Category.TONGUE_TWISTER){
            clickCount ++;
            if (clickCount < mExModel.shortDescIds.length){
                setShortDesc();
            }
            if (clickCount >= mExModel.shortDescIds.length){
                clickCount = 0;
                setShortDesc();
                setWord();
            }
        }else {
            setWord();
        }

    }

    private void setWord(){
        mChronometerOneWord.reset();
        List<String> words;
        switch (mExModel.name){
            case LINGUISTIC_PYRAMIDS:
                words = mRepo.getWords(WordType.NOT_ALIVE, mResources);
                _word.postValue(words.get(mRandom.nextInt(words.size())));
                break;
            case EASY_TONGUE_TWISTERS:
                words = mRepo.getWords(WordType.EASY_T_T, mResources);
                _word.postValue(words.get(mRandom.nextInt(words.size())));
                break;
        }
    }

    private void setShortDesc() {
        if (mExModel.category == ExModel.Category.TONGUE_TWISTER) {
            _shortDesc.postValue(mExModel.shortDescIds[clickCount]);
        }else {
            _shortDesc.postValue(mExModel.shortDescIds[0]);
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
}
