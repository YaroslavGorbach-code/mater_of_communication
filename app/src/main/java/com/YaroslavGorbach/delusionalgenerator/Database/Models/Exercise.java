package com.YaroslavGorbach.delusionalgenerator.Database.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises_table")
public class Exercise {

    @PrimaryKey
    public int id;

    public String name;
    public int category;
    public int pic;
    public int favorite;

    public Exercise(int id, String name, int category, int pic, int favorite) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.pic = pic;
        this.favorite = favorite;
    }

}
