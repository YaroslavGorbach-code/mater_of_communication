package com.YaroslavGorbach.delusionalgenerator.data;

public class DailyTrainingM {
    public long data;
    public Long oldData;
    public int progress;
    public int days;
    public Exercise.Name[] exNames;

    public DailyTrainingM(long data, Long oldData, int progress, int days, Exercise.Name... exNames) {
        this.data = data;
        this.exNames = exNames;
        this.oldData = oldData;
        this.progress = progress;
        this.days = days;
    }
}
