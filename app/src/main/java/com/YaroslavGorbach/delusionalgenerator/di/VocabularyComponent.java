package com.YaroslavGorbach.delusionalgenerator.di;

import com.YaroslavGorbach.delusionalgenerator.component.vocabulary.Vocabulary;
import com.YaroslavGorbach.delusionalgenerator.component.vocabulary.VocabularyImp;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.TimerImp;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.VocabularyFragment;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@ViewModelScope
@Component(dependencies = AppComponent.class,
        modules = {CommonExercisesModule.class, VocabularyComponent.VocabularyModule.class, AdModule.class})
public interface VocabularyComponent {

    void inject(VocabularyFragment vocabularyFragment);
    @Component.Factory
    interface Factory {
        VocabularyComponent create(
                @BindsInstance Exercise.Name name,
                @BindsInstance Exercise.Type type,
                AppComponent appComponent);
    }

    @Module
    class VocabularyModule {

        @ViewModelScope
        @Provides
        Timer provideTimer() {
            return new TimerImp();
        }

        @ViewModelScope
        @Provides
        Vocabulary provideVocabulary(
                Exercise.Name name,
                Exercise.Type type,
                Timer timer,
                StatisticsManager statisticsManager,
                Repo repo) {
            return new VocabularyImp(name, type, timer, statisticsManager, repo);
        }
    }



}
