package com.YaroslavGorbach.delusionalgenerator.data;

public class DailyTrainingEx {
    private final Exercise exercise;
    private final int aim;
    public int done;

    public DailyTrainingEx(Exercise exercise, int aim, int done) {
        this.aim = aim;
        this.exercise = exercise;
        this.done = done;
    }

    public int getAim() {
        return aim;
    }

    public Exercise getExercise() {
        return exercise;
    }

}
