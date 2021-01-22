package com.YaroslavGorbach.delusionalgenerator.ViewModels.Factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.ViewModels.DescriptionCategory1FragmentViewModel;

public class DescriptionCategory1FragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int id;
    private final Application application;
    public DescriptionCategory1FragmentViewModelFactory(Application application, int exId){
        super();
        id = exId;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DescriptionCategory1FragmentViewModel.class)) {
            return (T)  new DescriptionCategory1FragmentViewModel(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
