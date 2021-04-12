package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.RepoImpOLD;
import com.YaroslavGorbach.delusionalgenerator.screen.description.ExercisesDescriptionViewModel;

import java.util.List;

public class ExercisesVm extends ViewModel {
    private final Repo mRepo;

    public ExercisesVm(Repo repo) {
        mRepo = repo;
    }

    public List<ExModel> getAllExs() {
        return mRepo.getExercises();
    }

    public static class ExercisesVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;

        public ExercisesVmFactory(Repo repo){
            super();
            this.repo = repo;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ExercisesVm.class)) {
                return (T)  new ExercisesVm(repo);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
