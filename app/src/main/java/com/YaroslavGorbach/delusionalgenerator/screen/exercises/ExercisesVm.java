package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.exercises.Exercises;
import com.YaroslavGorbach.delusionalgenerator.component.exercises.ExercisesImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class ExercisesVm extends ViewModel {
    public final Exercises exercises;
    public ExercisesVm(Repo repo) {
       exercises = new ExercisesImp(repo);
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
