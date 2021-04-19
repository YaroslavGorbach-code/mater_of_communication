package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface StatisticsDao {

    @Insert
    void insert(Statistics statistics);

    @Query("SELECT * FROM statistics WHERE exId == :exId ORDER BY dataTime DESC LIMIT 15")
    List<Statistics> getStatisticsLast(int exId);

    @Query("SELECT * FROM statistics WHERE exId == :exId AND dataTime <:timeFrom ORDER BY dataTime DESC LIMIT 15")
    List<Statistics> getStatisticsPrevious(int exId, long timeFrom);

    @Query("SELECT * FROM statistics WHERE exId == :exId AND dataTime > :timeLast ORDER BY dataTime DESC LIMIT 15")
    List<Statistics> getStatisticsNext(int exId, long timeLast);
}
