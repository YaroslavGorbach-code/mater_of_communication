package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.VocabularyVm;

public class ExerciseCategory2ViewModelFactory  extends ViewModelProvider.NewInstanceFactory{

    private int id;
    private Application application;
    public ExerciseCategory2ViewModelFactory(Application application, int exId){
        super();
        id = exId;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VocabularyVm.class)) {
            return (T)  new VocabularyVm(application, id);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
