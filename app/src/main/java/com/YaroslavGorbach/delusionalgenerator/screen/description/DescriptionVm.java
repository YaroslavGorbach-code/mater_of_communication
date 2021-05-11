package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.component.description.Description;
import com.YaroslavGorbach.delusionalgenerator.component.description.DescriptionImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
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
