package com.YaroslavGorbach.delusionalgenerator.di;

import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.component.speaking.Speaking;
import com.YaroslavGorbach.delusionalgenerator.component.speaking.SpeakingImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorder;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking.SpeakingFragment;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@ViewModelScope
@Component(dependencies = AppComponent.class,
        modules = {SpeakingComponent.SpeakingModule.class, CommonExercisesModule.class, AdModule.class})
public interface SpeakingComponent {

    void inject(SpeakingFragment speakingFragment);

    @Component.Factory
    interface Factory{
        SpeakingComponent create(
                @BindsInstance Exercise.Name name,
                @BindsInstance Exercise.Type type,
                @BindsInstance Resources res,
                AppComponent appComponent);
    }

    @Module
    class SpeakingModule {

        @ViewModelScope
        @Provides
        VoiceRecorder provideVoiceRecorder() {
            return new VoiceRecorderImp();
        }


        @ViewModelScope
        @Provides
        Speaking provideSpeakingEx(Exercise.Name name,
                                   Exercise.Type type,
                                   Repo repo,
                                   StatisticsManager statisticsManager,
                                   Resources res,
                                   VoiceRecorder voiceRecorder) {
            return new SpeakingImp(name, type, repo, statisticsManager, res, voiceRecorder);
        }
    }
}
