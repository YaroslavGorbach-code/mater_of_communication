package com.YaroslavGorbach.delusionalgenerator.component.exercises;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.Training;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class ExercisesImp implements Exercises {
    private final Repo mRepo;

    public ExercisesImp(Repo repo){
        mRepo = repo;
    }
    @Override
    public Observable<Training> getTraining() {
        return mRepo.getTraining();
    }

    @Override
    public List<Exercise> getExercises() {
        return mRepo.getExercises();
    }

    @Override
    public List<Exercise> getExercises(Exercise.Category category) {
        return mRepo.getExercises(category);
    }
}
