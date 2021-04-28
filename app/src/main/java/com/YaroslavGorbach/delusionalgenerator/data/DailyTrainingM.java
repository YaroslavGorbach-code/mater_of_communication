package com.YaroslavGorbach.delusionalgenerator.data;

import io.reactivex.rxjava3.core.Single;

public class DailyTrainingM {
    public long data;
    public Long oldData;
    public int progress;
    public int days;
    public DailyTrainingEx[] exercises;

    public DailyTrainingM(long data, Long oldData, int progress, int days, DailyTrainingEx... exercises) {
        this.data = data;
        this.exercises = exercises;
        this.oldData = oldData;
        this.progress = progress;
        this.days = days;
    }
}
