package com.YaroslavGorbach.delusionalgenerator.component.dailyTraining;

import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingEx;
import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingM;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public class DailyTrainingImp implements DailyTraining {
    private final Repo mRepo;
    private final DailyTrainingM mDailyTraining;

    public DailyTrainingImp(Repo repo){
        mRepo = repo;
        mDailyTraining = mRepo.getDailyTraining();
    }
    @Override
    public int getProgress() {
        return mDailyTraining.progress;
    }

    @Override
    public int getDays() {
        return mDailyTraining.days;
    }

    @Override
    public Single<List<DailyTrainingEx>> getExercises() {
         return Observable.fromIterable(Arrays.asList(mDailyTraining.exNames))
                 .flatMap((Function<Exercise.Name, ObservableSource<DailyTrainingEx>>) name ->
                         Observable.just(new DailyTrainingEx()))
                 .toList();
    }
}
