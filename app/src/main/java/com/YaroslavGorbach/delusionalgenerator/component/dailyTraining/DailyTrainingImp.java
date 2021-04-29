package com.YaroslavGorbach.delusionalgenerator.component.dailyTraining;

import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingM;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import io.reactivex.rxjava3.core.Observable;

public class DailyTrainingImp implements DailyTraining {
    private final Repo mRepo;
    private final Observable<DailyTrainingM> mDailyTraining;

    public DailyTrainingImp(Repo repo) {
        mRepo = repo;
        mDailyTraining = mRepo.getDailyTraining();
    }


    @Override
    public Observable<DailyTrainingM> getDailyTraining() {
        return mDailyTraining;
    }

    @Override
    public void changeExProgress(Exercise exercise) {
        // TODO: 4/29/2021 fix it
       DailyTrainingM dailyTrainingM = mDailyTraining.blockingFirst();

       Observable.fromIterable(dailyTrainingM.exercises).map(dailyTrainingEx1 -> {
           if (exercise.getName() == dailyTrainingEx1.getName())
                dailyTrainingEx1.setDone(dailyTrainingEx1.getDone() + 1);
           return dailyTrainingEx1;
       }).toList().subscribe(dailyTrainingExes -> {
           dailyTrainingM.exercises.clear();
           dailyTrainingM.exercises.addAll(dailyTrainingExes);
           mRepo.updateDailyTraining(dailyTrainingM);
       });

    }
}
