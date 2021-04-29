package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class DailyTrainingM {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long data;
    public Long oldData;
    public int progress;
    public int days;
    public ArrayList<DailyTrainingEx> exercises;

    public DailyTrainingM(long data, Long oldData, int progress, int days, ArrayList<DailyTrainingEx> exercises) {
        this.data = data;
        this.exercises = exercises;
        this.oldData = oldData;
        this.progress = progress;
        this.days = days;
    }

}
