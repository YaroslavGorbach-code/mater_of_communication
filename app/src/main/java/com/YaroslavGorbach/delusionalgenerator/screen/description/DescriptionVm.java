package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerDescriptionComponent;
import com.YaroslavGorbach.delusionalgenerator.di.DescriptionComponent;

public class DescriptionVm extends AndroidViewModel {
    private DescriptionComponent descriptionComponent = null;

    public DescriptionVm(@NonNull Application application) {
        super(application);
    }

    public DescriptionComponent getDescriptionComponent(Exercise.Name name) {
        if (descriptionComponent == null){
            descriptionComponent = DaggerDescriptionComponent.factory().create(name, ((App)getApplication()).appComponent);
        }
        return descriptionComponent;
    }
}
