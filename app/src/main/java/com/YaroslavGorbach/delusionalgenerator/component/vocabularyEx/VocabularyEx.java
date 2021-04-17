package com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx;

import androidx.lifecycle.LiveData;

public interface VocabularyEx {
    void onClick();
    int getShortDescId();
    LiveData<Integer> getClickCount();
    LiveData<Long> getTimerValue();
}
