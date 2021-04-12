package com.YaroslavGorbach.delusionalgenerator.screen.exercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ExerciseCategory1ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private int id;
    private Application application;
    public ExerciseCategory1ViewModelFactory(Application application, int exId){
        super();
        id = exId;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ExerciseCategory1ViewModel.class)) {
            return (T)  new ExerciseCategory1ViewModel(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
