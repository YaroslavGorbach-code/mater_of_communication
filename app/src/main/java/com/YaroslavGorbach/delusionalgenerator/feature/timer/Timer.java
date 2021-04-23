package com.YaroslavGorbach.delusionalgenerator.feature.timer;

import androidx.lifecycle.LiveData;

public interface Timer {
    void start();
    LiveData<Boolean> onFinish();
    LiveData<String> getValue();
}
