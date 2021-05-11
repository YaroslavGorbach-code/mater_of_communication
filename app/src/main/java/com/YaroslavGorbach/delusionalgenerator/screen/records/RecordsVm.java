package com.YaroslavGorbach.delusionalgenerator.screen.records;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsList;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;

public class RecordsVm extends ViewModel {
    public RecordsList recordsList;
    public AdManager adManager;

    public RecordsVm(RecordsList recordsList, AdManager adManager) {
        this.recordsList = recordsList;
        this.adManager = adManager;
    }

    public static class RecordsVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final RecordsList recordsList;
        private final AdManager adManager;

        public RecordsVmFactory(RecordsList recordsList, AdManager adManager) {
            super();
            this.recordsList = recordsList;
            this.adManager = adManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(RecordsVm.class)) {
                return (T) new RecordsVm(recordsList, adManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}