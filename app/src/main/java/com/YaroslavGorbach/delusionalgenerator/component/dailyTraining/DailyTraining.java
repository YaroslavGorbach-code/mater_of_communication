package com.YaroslavGorbach.delusionalgenerator.component.dailyTraining;

import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingEx;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;

public interface DailyTraining {
    int getProgress();
    int getDays();
    Single<List<DailyTrainingEx>> getExercises();
}
