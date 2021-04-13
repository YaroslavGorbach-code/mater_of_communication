package com.YaroslavGorbach.delusionalgenerator.component;

import androidx.lifecycle.LiveData;

public interface SpeakingEx {
    void nextWord();
    String getShortDesc();
    LiveData<String> getWord();
}
