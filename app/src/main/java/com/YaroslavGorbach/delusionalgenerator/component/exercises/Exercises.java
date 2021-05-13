package com.YaroslavGorbach.delusionalgenerator.component.exercises;

import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface Exercises {
    Observable<Training> getTraining();
    List<Exercise> getExercises();
    List<Exercise> getExercises(Exercise.Category category);
}
