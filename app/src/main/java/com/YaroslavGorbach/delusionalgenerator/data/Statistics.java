package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Statistics {
    @PrimaryKey(autoGenerate = true)
    private int id;
    public int exId;
    public int value;
    public long dataTime;

    public Statistics(int exId, int value, long dataTime) {
        this.exId = exId;
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
