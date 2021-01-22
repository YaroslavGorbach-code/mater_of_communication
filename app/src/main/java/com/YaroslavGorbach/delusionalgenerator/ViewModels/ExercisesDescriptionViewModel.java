package com.YaroslavGorbach.delusionalgenerator.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;

public class ExercisesDescriptionViewModel extends AndroidViewModel {
    private final Repo_2 mRepo;

    public ExercisesDescriptionViewModel(@NonNull Application application, int exId) {
        super(application);
        mRepo = new Repo_2(application);
    }

    public void update(Exercise exercise){
        mRepo.update(exercise);
    }

    public LiveData<Exercise> getExerciseById(int id) {
        return mRepo.getExerciseById(id);
    }
}
