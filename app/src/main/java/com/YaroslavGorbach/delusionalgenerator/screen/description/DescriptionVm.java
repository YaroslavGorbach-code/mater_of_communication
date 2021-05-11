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

    public DescriptionVm(Description description){
       this.description = description;
    }

    public static class DescriptionVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Description description;

        public DescriptionVmFactory(Description description){
            super();
            this.description = description;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(DescriptionVm.class)) {
                return (T)  new DescriptionVm(description);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
