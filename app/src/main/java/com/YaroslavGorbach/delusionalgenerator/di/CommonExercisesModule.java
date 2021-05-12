package com.YaroslavGorbach.delusionalgenerator.di;

import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorder;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonExercisesModule {

    @ViewModelScope
    @Provides
    VoiceRecorder provideVoiceRecorder(){
        return new VoiceRecorderImp();
    }

    @ViewModelScope
    @Provides
    StatisticsManager provideStatisticsManager(){
        return new StatisticsManagerImp();
    }
}
