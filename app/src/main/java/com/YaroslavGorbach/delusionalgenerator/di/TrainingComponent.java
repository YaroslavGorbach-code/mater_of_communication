package com.YaroslavGorbach.delusionalgenerator.di;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;
import com.YaroslavGorbach.delusionalgenerator.screen.training.TrainingFragment;


import dagger.Component;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Observable;

@ViewModelScope
@Component(dependencies = AppComponent.class, modules = {TrainingComponent.TrainingModule.class})
public interface TrainingComponent {

    void inject(TrainingFragment trainingFragment);

    @Component.Factory
    interface Factory {
        TrainingComponent create(AppComponent appComponent);
    }

    @Module
    class TrainingModule{

        @ViewModelScope
        @Provides
        Observable<Training> provideTraining(Repo repo){
            return repo.getTraining();
        }
    }
}
