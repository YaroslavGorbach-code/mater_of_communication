package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;

import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {

    private final Repo_2 mRepo;

    public ExercisesViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repo_2(application);
    }

    public void insertEx(Exercise exercise){
        mRepo.insertEx(exercise);
    }

    public void deleteEx(Exercise exercise){
        mRepo.deleteEx(exercise);
    }

    public void update(Exercise exercise){
        mRepo.update(exercise);
    }

    public LiveData<List<Exercise>> getExByCategory(int category) {
        return mRepo.getExByCategory(category);
    }

}
