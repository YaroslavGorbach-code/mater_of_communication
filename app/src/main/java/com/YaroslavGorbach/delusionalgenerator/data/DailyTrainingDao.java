package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface DailyTrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DailyTrainingM dailyTrainingM);

    @Query("SELECT * FROM dailytrainingm LIMIT 1")
    DailyTrainingM getDailyTraining();
}
