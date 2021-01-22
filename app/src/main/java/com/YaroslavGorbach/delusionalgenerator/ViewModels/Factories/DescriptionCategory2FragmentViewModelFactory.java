package com.YaroslavGorbach.delusionalgenerator.ViewModels.Factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.ViewModels.DescriptionCategory2FragmentViewModel;

public class DescriptionCategory2FragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int id;
    private final Application application;
    public DescriptionCategory2FragmentViewModelFactory(Application application, int exId){
        super();
        id = exId;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DescriptionCategory2FragmentViewModel.class)) {
            return (T)  new DescriptionCategory2FragmentViewModel(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
