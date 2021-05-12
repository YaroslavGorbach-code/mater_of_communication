package com.YaroslavGorbach.delusionalgenerator.di;

import android.content.Context;

import com.YaroslavGorbach.delusionalgenerator.data.InMemoryDbImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.RepoImp;
import com.YaroslavGorbach.delusionalgenerator.data.RepoProvider;
import com.YaroslavGorbach.delusionalgenerator.data.SharedPrefStorageImp;
import com.YaroslavGorbach.delusionalgenerator.data.room.RoomDb;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@Singleton
@Component(modules = RepoComponent.RepoModule.class)
public interface RepoComponent extends RepoProvider {

    @Component.Factory
    interface Factory{
        RepoComponent create(@BindsInstance Context context);
    }

    @Module
    class RepoModule {

        @Singleton
        @Provides
        public Repo provideRepo(Context context){
            return new RepoImp(RoomDb.getInstance(context), new InMemoryDbImp(), new SharedPrefStorageImp(context));
        }
    }
}
