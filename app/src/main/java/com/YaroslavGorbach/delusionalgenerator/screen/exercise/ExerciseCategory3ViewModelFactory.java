package com.YaroslavGorbach.delusionalgenerator.screen.exercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ExerciseCategory3ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private int id;
    private Application application;
    public ExerciseCategory3ViewModelFactory(Application application, int exId){
        super();
        id = exId;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ExerciseCategory3ViewModel.class)) {
            return (T)  new ExerciseCategory3ViewModel(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
