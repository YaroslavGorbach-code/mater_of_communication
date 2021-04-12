package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Description_dao {
    @Insert
    void insert(Description_category_1 descriptionCategory1);

    @Insert
    void insert(Description_category_2 descriptionCategory2);

    @Insert
    void insert(Description_category_3 descriptionCategory3);


    @Query("SELECT * FROM description_category_1_table WHERE exId = :exId")
    LiveData<Description_category_1> getDescription_category_1_ByExId(int exId);

    @Query("SELECT * FROM description_category_2_table WHERE exId = :exId")
    LiveData<Description_category_2> getDescription_category_2_ByExId(int exId);

    @Query("SELECT * FROM description_category_3_table WHERE exId = :exId")
    LiveData<Description_category_3> getDescription_category_3_ByExId(int exId);

}
