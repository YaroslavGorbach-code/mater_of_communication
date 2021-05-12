package com.YaroslavGorbach.delusionalgenerator.di;

import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonExercisesModule {

    @ViewModelScope
    @Provides
    StatisticsManager provideStatisticsManager() {
        return new StatisticsManagerImp();
    }
}
