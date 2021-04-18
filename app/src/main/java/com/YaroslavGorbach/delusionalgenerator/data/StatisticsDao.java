package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface StatisticsDao {

    @Insert
    void insert(Statistics statistics);

    @Query("SELECT * FROM statistics WHERE exId == :exId ORDER BY dataTime DESC LIMIT 10")
    List<Statistics> getData(int exId);
}
