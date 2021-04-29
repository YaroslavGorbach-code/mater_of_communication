package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class DailyTrainingM {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long date;
    public int progress;
    public int days;
    public ArrayList<DailyTrainingEx> exercises;

    public DailyTrainingM(long date, int progress, int days, ArrayList<DailyTrainingEx> exercises) {
        this.date = date;
        this.exercises = exercises;
        this.progress = progress;
        this.days = days;
    }

}
