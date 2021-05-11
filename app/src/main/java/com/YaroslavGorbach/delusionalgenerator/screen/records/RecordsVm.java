package com.YaroslavGorbach.delusionalgenerator.screen.records;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsList;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerRecordsComponent;
import com.YaroslavGorbach.delusionalgenerator.di.RecordsComponent;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;

public class RecordsVm extends AndroidViewModel {
    public RecordsComponent recordsComponent;

    public RecordsVm(@NonNull Application application) {
        super(application);
        recordsComponent = DaggerRecordsComponent.factory().create(application, ((App)application).appComponent);
    }

}