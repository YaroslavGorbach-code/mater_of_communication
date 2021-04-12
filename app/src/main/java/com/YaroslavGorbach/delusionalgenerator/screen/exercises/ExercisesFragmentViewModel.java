package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

import java.util.List;

public class ExercisesFragmentViewModel extends AndroidViewModel {
    private final Repo mRepo;

    public ExercisesFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repo(application);
    }

    public LiveData<List<Exercise>> getExByCategory(int category) {
        return mRepo.getExByCategory(category);
    }

}
