package com.YaroslavGorbach.delusionalgenerator.ViewModels.Factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.ViewModels.ExercisesDescriptionViewModel;

public class ExercisesDescriptionViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int id;
    private final Application application;
    public ExercisesDescriptionViewModelFactory(Application application, int exId){
        super();
        id = exId;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ExercisesDescriptionViewModel.class)) {
            return (T)  new ExercisesDescriptionViewModel(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
