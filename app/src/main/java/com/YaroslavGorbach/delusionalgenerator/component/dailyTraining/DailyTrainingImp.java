package com.YaroslavGorbach.delusionalgenerator.component.dailyTraining;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class DailyTrainingImp implements DailyTraining {

    private final Repo mRepo;
    public DailyTrainingImp(Repo repo){
        mRepo = repo;
    }
    @Override
    public int getProgress() {
        return mRepo.getProgress();
    }

    @Override
    public int getDays() {
        return mRepo.getTrainingDays();
    }
}
