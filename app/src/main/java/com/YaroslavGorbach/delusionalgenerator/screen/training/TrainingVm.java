package com.YaroslavGorbach.delusionalgenerator.screen.training;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerTrainingComponent;
import com.YaroslavGorbach.delusionalgenerator.di.TrainingComponent;

import io.reactivex.rxjava3.core.Observable;

public class TrainingVm extends AndroidViewModel {
    public final TrainingComponent trainingComponent;
    public TrainingVm(@NonNull Application application) {
        super(application);
        trainingComponent = DaggerTrainingComponent.factory().create(((App)application).appComponent);
    }
}
