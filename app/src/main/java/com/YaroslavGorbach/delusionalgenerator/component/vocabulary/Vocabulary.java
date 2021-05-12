package com.YaroslavGorbach.delusionalgenerator.component.vocabulary;

import androidx.lifecycle.LiveData;

public interface Vocabulary {
    void onClick();

    int getShortDescId();

    Result getResultState();

    void saveStatistics();

    LiveData<Integer> getClickCount();

    LiveData<String> getTimerValue();

    LiveData<Boolean> onTimerFinish();

    enum Result {
        GOOD(),
        BAD(),
        VERY_GOOD();
        int numberWords;

        public int getNumberWords() {
            return numberWords;
        }

        public void setNumberWords(int numberWords) {
            this.numberWords = numberWords;
        }
    }
}
