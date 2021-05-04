package com.YaroslavGorbach.delusionalgenerator.component.exercises;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface Exercises {
    Observable<Training> getTraining();
    List<Exercise> getExercises();
    List<Exercise> getExercises(Exercise.Category category);
}
