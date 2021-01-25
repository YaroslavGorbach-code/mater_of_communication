package com.YaroslavGorbach.delusionalgenerator.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;

public class ExercisesDescriptionViewModel extends AndroidViewModel {
    private final Repo mRepo;

    public ExercisesDescriptionViewModel(@NonNull Application application, int exId) {
        super(application);
        mRepo = new Repo(application);
    }

    public void update(Exercise exercise){
        mRepo.update(exercise);
    }

    public LiveData<Exercise> getExerciseById(int id) {
        return mRepo.getExerciseById(id);
    }
}
