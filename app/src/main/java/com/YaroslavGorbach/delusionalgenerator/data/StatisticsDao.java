package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StatisticsDao {

    @Insert
    void insert(Statistics statistics);

    @Query("SELECT * FROM statistics WHERE exName = :arg0 ORDER BY dataTime")
    List<Statistics> getStatistics(Exercise.Name arg0);
}
