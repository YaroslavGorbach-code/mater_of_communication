package com.YaroslavGorbach.delusionalgenerator.di;

import com.YaroslavGorbach.delusionalgenerator.component.description.Description;
import com.YaroslavGorbach.delusionalgenerator.component.description.DescriptionImp;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.description.DescriptionFragment;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@ViewModelScope
@Component(dependencies = AppComponent.class, modules = DescriptionComponent.DescriptionModule.class)
public interface DescriptionComponent {

    void inject(DescriptionFragment descriptionFragment);

    @Component.Factory
    interface Factory {
        DescriptionComponent create(@BindsInstance Exercise.Name name, AppComponent appComponent);
    }

    @Module
    class DescriptionModule{

        @ViewModelScope
        @Provides
        Description provideDescription(Repo repo, Exercise.Name name){
            return new DescriptionImp(repo, name);
        }
    }
}
