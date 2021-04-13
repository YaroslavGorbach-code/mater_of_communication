package com.YaroslavGorbach.delusionalgenerator.component;

import androidx.lifecycle.LiveData;

public interface SpeakingEx {
    void nextWord();
    LiveData<String> getWord();
}
