package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// TODO: 1/24/2021 не забыть добавть эту таблицу в миграцию
@Entity(tableName = "description_category_1_table")
public class Description_category_1 {

    @PrimaryKey
    public int exId;
    public String aim;
    public String description;
    public String example;
    public String period;

    public Description_category_1(int exId, String aim, String description, String example, String period) {
        this.exId = exId;
        this.aim = aim;
        this.description = description;
        this.example = example;
        this.period = period;
    }
}