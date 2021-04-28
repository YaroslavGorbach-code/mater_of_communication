package com.YaroslavGorbach.delusionalgenerator.screen.dailyTraining;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.dailyTraining.DailyTraining;
import com.YaroslavGorbach.delusionalgenerator.component.dailyTraining.DailyTrainingImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.description.DescriptionVm;

public class DailyTrainingVm extends ViewModel {
    public final DailyTraining dailyTraining;

    public DailyTrainingVm(Repo repo){
        dailyTraining = new DailyTrainingImp(repo);
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
            if (modelClass.isAssignableFrom(DailyTrainingVm.class)) {
                return (T)  new DailyTrainingVm(repo);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
