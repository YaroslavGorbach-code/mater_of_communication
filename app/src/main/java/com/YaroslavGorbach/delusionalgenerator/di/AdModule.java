package com.YaroslavGorbach.delusionalgenerator.di;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;

import dagger.Module;
import dagger.Provides;

@Module
public class AdModule {
    @ViewModelScope
    @Provides
    public AdManager provideAdManager(Repo repo) {
        return new AdManagerImp(repo);
    }
}

