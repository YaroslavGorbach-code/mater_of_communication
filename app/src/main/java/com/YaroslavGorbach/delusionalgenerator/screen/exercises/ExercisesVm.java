package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.exercises.Exercises;
import com.YaroslavGorbach.delusionalgenerator.component.exercises.ExercisesImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;

public class ExercisesVm extends ViewModel {
    public final Exercises exercises;
    public final AdManager adManager;

    public ExercisesVm(Exercises exercises, AdManager adManager) {
      this.exercises = exercises;
      this.adManager = adManager;
    }

    public static class ExercisesVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Exercises exercises;
        private final AdManager adManager;

        public ExercisesVmFactory(Exercises exercises, AdManager adManager){
            super();
            this.exercises = exercises;
            this.adManager = adManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ExercisesVm.class)) {
                return (T)  new ExercisesVm(exercises, adManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
