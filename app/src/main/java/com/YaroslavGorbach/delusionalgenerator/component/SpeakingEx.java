package com.YaroslavGorbach.delusionalgenerator.component;

import android.widget.Chronometer;

import androidx.lifecycle.LiveData;

public interface SpeakingEx {
    void nextWord();
    void startPauseChronometer();
    String getShortDesc();
    LiveData<String> getWord();
}
