package com.YaroslavGorbach.delusionalgenerator.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;

import java.util.List;

@Dao
public interface Exercise_dao {
    @Insert
    void insert(Exercise exercise);

    @Update
    void update(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

    @Query("SELECT * FROM exercises_table WHERE category = :category ORDER BY id ASC")
    LiveData<List<Exercise>> getExercisesByCategory(int category);

    @Query("SELECT * FROM exercises_table WHERE id = :id")
    LiveData<Exercise> getExerciseById(int id);

    @Query("SELECT * FROM exercises_table WHERE favorite = 1 ORDER BY id ASC")
    LiveData<List<Exercise>> getFavoriteExercises();

}
