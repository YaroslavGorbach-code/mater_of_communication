package com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx;

import androidx.lifecycle.LiveData;

public interface VocabularyEx {
    void onClick();
    String getShortDesc();
    LiveData<Integer> getClickCount();
    LiveData<Long> getTimerValue();
}
