package com.YaroslavGorbach.delusionalgenerator.data.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;

@Entity
public class Statistics {
    @PrimaryKey(autoGenerate = true)
    private int id;
    public Exercise.Name exName;
    public int value;
    public long time;

    public Statistics(Exercise.Name exName, int value, long time) {
        this.exName = exName;
        this.value = value;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
