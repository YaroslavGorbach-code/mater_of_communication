package com.YaroslavGorbach.delusionalgenerator.component;

import android.widget.Chronometer;

import androidx.lifecycle.LiveData;

public interface SpeakingEx {
    void nextWord();
    void startPauseChronometer();
    void startStopRecord();
    LiveData<Boolean> getRecordingState();
    String getShortDesc();
    LiveData<String> getWord();
}
