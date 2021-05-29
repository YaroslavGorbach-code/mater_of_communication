package com.YaroslavGorbach.delusionalgenerator.data.local.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Statistics;

import java.util.List;

@Dao
public interface StatisticsDao {

    @Insert
    void insert(Statistics statistics);

    @Query("SELECT * FROM statistics WHERE exName = :arg0 ORDER BY time")
    List<Statistics> getStatistics(Exercise.Name arg0);
}
