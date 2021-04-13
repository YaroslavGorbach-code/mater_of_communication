package com.YaroslavGorbach.delusionalgenerator.feature.chronometer;

import androidx.lifecycle.LiveData;

public interface Chronometer {
    void start();
    void pause();
    void reset();
    boolean getState();
}
