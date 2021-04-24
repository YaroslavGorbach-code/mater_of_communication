package com.YaroslavGorbach.delusionalgenerator.screen.description;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.description.Description;
import com.YaroslavGorbach.delusionalgenerator.component.description.DescriptionImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class DescriptionVm extends ViewModel {

    public final Description description;

    public DescriptionVm(Repo repo, Exercise.Name name){
       description = new DescriptionImp(repo, name);
    }

    public static class DescriptionVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final Exercise.Name name;

        public DescriptionVmFactory(Repo repo, Exercise.Name name){
            super();
            this.repo = repo;
            this.name = name;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(DescriptionVm.class)) {
                return (T)  new DescriptionVm(repo, name);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
