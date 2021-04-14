package com.YaroslavGorbach.delusionalgenerator.component.tonguetwister_ex;

import androidx.lifecycle.LiveData;

public interface TongueTwisterEx {
    void onNextClick();
    LiveData<String> getTongueTwister();
    LiveData<String> getShortDesc();
}
