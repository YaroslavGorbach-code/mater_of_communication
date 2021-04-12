package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// TODO: 1/24/2021 не забыть добавть эту таблицу в миграцию
@Entity(tableName = "description_category_2_table")
public class Description_category_2 {
    @PrimaryKey
    public int exId;
    public String aim;
    public String description;
    public String period;

    public Description_category_2(int exId, String aim, String description, String period) {
        this.exId = exId;
        this.aim = aim;
        this.description = description;
        this.period = period;
    }
}