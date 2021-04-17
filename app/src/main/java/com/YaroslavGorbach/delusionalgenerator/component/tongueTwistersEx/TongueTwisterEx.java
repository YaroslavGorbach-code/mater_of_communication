package com.YaroslavGorbach.delusionalgenerator.component.tongueTwistersEx;

import androidx.lifecycle.LiveData;

public interface TongueTwisterEx {
    void onNextClick();
    LiveData<String> getTongueTwister();
    LiveData<Integer> getShortDescId();
}
