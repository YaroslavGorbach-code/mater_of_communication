package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.app.Application;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerSpeakingComponent;
import com.YaroslavGorbach.delusionalgenerator.di.SpeakingComponent;

public class SpeakingVm extends AndroidViewModel {
    private SpeakingComponent speakingComponent = null;

    public SpeakingVm(@NonNull Application application) {
        super(application);
    }

    public SpeakingComponent getSpeakingComponent(Exercise.Name name, Exercise.Type type, Resources res) {
        if (speakingComponent == null) {
             speakingComponent = DaggerSpeakingComponent.factory().create(name, type, res, ((App)getApplication()).appComponent);
        }
        return speakingComponent;
    }

}
