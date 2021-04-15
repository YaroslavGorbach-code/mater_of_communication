package com.YaroslavGorbach.delusionalgenerator.component.tonguetwistersEx;

import androidx.lifecycle.LiveData;

public interface TongueTwisterEx {
    void onNextClick();
    LiveData<String> getTongueTwister();
    LiveData<String> getShortDesc();
}
