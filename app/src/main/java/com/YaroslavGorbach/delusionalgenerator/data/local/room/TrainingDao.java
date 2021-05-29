package com.YaroslavGorbach.delusionalgenerator.data.local.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface TrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Training training);

    @Query("SELECT * FROM Training ORDER BY date DESC LIMIT 1")
    Observable<Training> getDailyTraining();

}
