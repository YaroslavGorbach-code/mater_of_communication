package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.Training;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class ExercisesVm extends ViewModel {
    public final Observable<Training> training;
    public final List<Exercise> exercises;

    public ExercisesVm(Repo repo) {
        training = repo.getTraining();
        exercises = repo.getExercises();
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
