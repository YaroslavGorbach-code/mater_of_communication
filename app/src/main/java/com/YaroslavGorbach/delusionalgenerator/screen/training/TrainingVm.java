package com.YaroslavGorbach.delusionalgenerator.screen.training;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerTrainingComponent;
import com.YaroslavGorbach.delusionalgenerator.di.TrainingComponent;

public class TrainingVm extends AndroidViewModel {
    public final TrainingComponent trainingComponent;
    public TrainingVm(@NonNull Application application) {
        super(application);
        trainingComponent = DaggerTrainingComponent.factory().create(((App)application).appComponent);
    }
}
