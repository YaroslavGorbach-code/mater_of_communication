package com.YaroslavGorbach.delusionalgenerator.screen.training;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

import io.reactivex.rxjava3.core.Observable;

public class TrainingVm extends ViewModel {
    public final Observable<Training> training;

    public TrainingVm(Repo repo){
        training = repo.getTraining();
    }

    public static class DailyTrainingVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;

        public DailyTrainingVmFactory(Repo repo){
            super();
            this.repo = repo;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(TrainingVm.class)) {
                return (T)  new TrainingVm(repo);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
