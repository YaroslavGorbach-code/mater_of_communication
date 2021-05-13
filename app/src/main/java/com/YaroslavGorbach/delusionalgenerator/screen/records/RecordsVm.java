package com.YaroslavGorbach.delusionalgenerator.screen.records;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerRecordsComponent;
import com.YaroslavGorbach.delusionalgenerator.di.RecordsComponent;

public class RecordsVm extends AndroidViewModel {
    public RecordsComponent recordsComponent;

    public RecordsVm(@NonNull Application application) {
        super(application);
        recordsComponent = DaggerRecordsComponent.factory().create(application, ((App)application).appComponent);
    }

}