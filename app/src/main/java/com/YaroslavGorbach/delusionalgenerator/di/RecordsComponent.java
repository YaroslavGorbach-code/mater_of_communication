package com.YaroslavGorbach.delusionalgenerator.di;

import android.content.Context;

import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsList;
import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsListImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.records.RecordsFragment;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@ViewModelScope
@Component(dependencies = AppComponent.class,
        modules = {RecordsComponent.RecordsModule.class, AdModule.class})
public interface RecordsComponent {
    void inject(RecordsFragment recordsFragment);

    @Component.Factory
    interface Factory {
        RecordsComponent create(@BindsInstance Context context, AppComponent appComponent);
    }

    @Module
    class RecordsModule{

        @ViewModelScope
        @Provides
        public RecordsList provideRecordsList(Repo repo, Context context){
            return new RecordsListImp(repo, context);
        }

    }
}
