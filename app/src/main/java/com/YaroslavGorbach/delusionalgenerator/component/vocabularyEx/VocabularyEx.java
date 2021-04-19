package com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx;

import androidx.lifecycle.LiveData;

public interface VocabularyEx {
    void onClick();
    int getShortDescId();
    Result getResultState();
    void saveStatistics();
    LiveData<Integer> getClickCount();
    LiveData<Long> getTimerValue();
    LiveData<Boolean> onTimerFinish();

    enum Result{
        GOOD(),
        BAD(),
        VERY_GOOD();
        int number;
        public int getNumber() {
            return number;
        }
        public void setNumber(int number) {
            this.number = number;
        }
    }
}
