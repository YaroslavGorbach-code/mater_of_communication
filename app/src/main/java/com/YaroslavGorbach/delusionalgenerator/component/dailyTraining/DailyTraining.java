package com.YaroslavGorbach.delusionalgenerator.component.dailyTraining;

import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingM;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;

import io.reactivex.rxjava3.core.Observable;

public interface DailyTraining {
    Observable<DailyTrainingM> getDailyTraining();
    void changeExProgress(Exercise exercise);
}
