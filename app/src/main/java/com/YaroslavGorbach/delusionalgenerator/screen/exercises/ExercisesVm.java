package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerExercisesComponent;
import com.YaroslavGorbach.delusionalgenerator.di.ExercisesComponent;

public class ExercisesVm extends AndroidViewModel {
    public ExercisesComponent exercisesComponent;

    public ExercisesVm(@NonNull Application application) {
        super(application);
        exercisesComponent = DaggerExercisesComponent.factory().create(((App) application).appComponent);
    }
}
