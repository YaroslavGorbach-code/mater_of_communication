package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.RepoImpOLD;

public class ExercisesDescriptionViewModel extends AndroidViewModel {
    private final RepoImpOLD mRepoImpOLD;

    public ExercisesDescriptionViewModel(@NonNull Application application, int exId) {
        super(application);
        mRepoImpOLD = new RepoImpOLD(application);
    }

    public void update(Exercise exercise){
        mRepoImpOLD.update(exercise);
    }

    public LiveData<Exercise> getExerciseById(int id) {
        return mRepoImpOLD.getExerciseById(id);
    }
}
