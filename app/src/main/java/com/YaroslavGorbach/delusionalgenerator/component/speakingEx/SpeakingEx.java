package com.YaroslavGorbach.delusionalgenerator.component.speakingEx;

import android.content.Context;

import androidx.lifecycle.LiveData;

public interface SpeakingEx {
    void onNext();
    void saveStatistics();
    void onStartStopRecord(Context context);
    LiveData<Boolean> getRecordingState();
    LiveData<Integer> getShortDescId();
    LiveData<String> getWord();
}
