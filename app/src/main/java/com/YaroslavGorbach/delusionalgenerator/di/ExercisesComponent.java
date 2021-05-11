package com.YaroslavGorbach.delusionalgenerator.di;

import com.YaroslavGorbach.delusionalgenerator.component.exercises.Exercises;
import com.YaroslavGorbach.delusionalgenerator.component.exercises.ExercisesImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExercisesFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.bycategory.ByCategoryFragment;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

@ViewModelScope
@Component(dependencies = AppComponent.class, modules = ExercisesComponent.ExercisesModule.class)
public interface ExercisesComponent {

    void inject(ExercisesFragment exercisesFragment);
    void inject(ByCategoryFragment byCategoryFragment);

    @Component.Factory
    interface Factory {
        ExercisesComponent create(AppComponent appComponent);
    }

    @Module
    class ExercisesModule {
        @ViewModelScope
        @Provides
        public Exercises provideExercises(Repo repo) {
            return new ExercisesImp(repo);
        }

        @ViewModelScope
        @Provides
        public AdManager provideAdManager(Repo repo) {
            return new AdManagerImp(repo);
        }
    }

}
