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
    public long dataTime;

    public Statistics(Exercise.Name exName, int value, long dataTime) {
        this.exName = exName;
        this.value = value;
        this.dataTime = dataTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
