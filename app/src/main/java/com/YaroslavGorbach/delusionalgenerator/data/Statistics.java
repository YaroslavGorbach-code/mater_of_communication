package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Statistics {
    @PrimaryKey(autoGenerate = true)
    private int id;
    public ExModel.Name exName;
    public int value;
    public long dataTime;

    public Statistics(ExModel.Name exName, int value, long dataTime) {
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
