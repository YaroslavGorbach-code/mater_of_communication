package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.rxjava3.core.Observable;


@Dao
public interface DailyTrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DailyTrainingM dailyTrainingM);

    @Query("SELECT * FROM dailytrainingm ORDER BY date DESC LIMIT 1")
    Observable<DailyTrainingM> getDailyTraining();
}
