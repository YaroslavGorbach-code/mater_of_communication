package com.YaroslavGorbach.delusionalgenerator.component.vocabulary;

import androidx.lifecycle.LiveData;

public interface VocabularyEx {
    void onClick();
    String getShortDesc();
    LiveData<Integer> getClickCount();
    LiveData<Long> getTimerValue();
}
