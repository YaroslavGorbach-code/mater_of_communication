package com.YaroslavGorbach.delusionalgenerator.component.dailyTraining;

import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingEx;
import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingM;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface DailyTraining {
    Observable<DailyTrainingM> getDailyTraining();
    void changeExProgress(DailyTrainingEx dailyTrainingEx);
}
