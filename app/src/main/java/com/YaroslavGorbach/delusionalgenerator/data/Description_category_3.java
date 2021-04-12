package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// TODO: 1/24/2021 не забыть добавть эту таблицу в миграцию
@Entity(tableName = "description_category_3_table")
public class Description_category_3 {
    @PrimaryKey
    public int exId;
    public String aim;
    public String description_part_1;
    public String description_part_2;
    public String description_part_3;
    public String description_part_4;
    public String description_part_5;

    public Description_category_3(
            int exId,String aim,
            String description_part_1,
            String description_part_2,
            String description_part_3,
            String description_part_4,
            String description_part_5) {

        this.exId = exId;
        this.aim = aim;
        this.description_part_1 = description_part_1;
        this.description_part_2 = description_part_2;
        this.description_part_3 = description_part_3;
        this.description_part_4 = description_part_4;
        this.description_part_5 = description_part_5;
    }
}