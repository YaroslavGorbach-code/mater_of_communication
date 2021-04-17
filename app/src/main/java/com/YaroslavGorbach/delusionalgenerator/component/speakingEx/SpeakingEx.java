package com.YaroslavGorbach.delusionalgenerator.component.speakingEx;

import android.content.Context;

import androidx.lifecycle.LiveData;

public interface SpeakingEx {
    void nextWord();
    void startPauseChronometer();
    void startStopRecord(Context context);
    LiveData<Boolean> getRecordingState();
    int getShortDescId();
    LiveData<String> getWord();
}
