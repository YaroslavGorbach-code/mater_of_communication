package com.YaroslavGorbach.delusionalgenerator.component.speaking;

import android.content.Context;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;

public interface Speaking {
    void onNext();
    void saveStatistics();
    void onStartStopRecord(Context context);
    void stopRecording();
    LiveData<Boolean> getRecordingState();
    LiveData<Integer> getShortDescId();
    LiveData<String> getWord();
    LiveData<Pair<Integer, Integer>> getDoneAndAim();
}
