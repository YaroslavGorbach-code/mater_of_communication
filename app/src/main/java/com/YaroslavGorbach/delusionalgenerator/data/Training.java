package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Training {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long date;
    public int progress;
    public int days;
    public ArrayList<Exercise> exercises;

    public Training(long date, int progress, int days, ArrayList<Exercise> exercises) {
        this.date = date;
        this.exercises = exercises;
        this.progress = progress;
        this.days = days;
    }

}
