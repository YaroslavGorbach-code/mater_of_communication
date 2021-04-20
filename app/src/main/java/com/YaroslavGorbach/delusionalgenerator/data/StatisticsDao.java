package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface StatisticsDao {

    @Insert
    void insert(Statistics statistics);

    @Query("SELECT * FROM statistics WHERE exName == :name ORDER BY dataTime DESC LIMIT 15")
    List<Statistics> getStatisticsLast(ExModel.Name name);

    @Query("SELECT * FROM statistics WHERE exName == :name AND dataTime <:timeFrom ORDER BY dataTime DESC LIMIT 15")
    List<Statistics> getStatisticsPrevious(ExModel.Name name, long timeFrom);

    @Query("SELECT * FROM statistics WHERE exName == :name AND dataTime > :timeLast ORDER BY dataTime DESC LIMIT 15")
    List<Statistics> getStatisticsNext(ExModel.Name name, long timeLast);
}
