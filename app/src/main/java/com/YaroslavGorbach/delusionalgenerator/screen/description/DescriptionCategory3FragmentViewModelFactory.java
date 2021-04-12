package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DescriptionCategory3FragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int id;
    private final Application application;
    public DescriptionCategory3FragmentViewModelFactory(Application application, int exId){
        super();
        id = exId;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DescriptionCategory3FragmentViewModel.class)) {
            return (T)  new DescriptionCategory3FragmentViewModel(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
