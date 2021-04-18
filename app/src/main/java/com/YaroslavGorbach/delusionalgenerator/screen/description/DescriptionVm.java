package com.YaroslavGorbach.delusionalgenerator.screen.description;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.Description.Description;
import com.YaroslavGorbach.delusionalgenerator.component.Description.DescriptionImp;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class DescriptionVm extends ViewModel {

    public final Description description;

    public DescriptionVm(Repo repo, int exId){
       description = new DescriptionImp(repo, exId);
    }


    public static class DescriptionVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final int exId;

        public DescriptionVmFactory(Repo repo, int exId){
            super();
            this.repo = repo;
            this.exId = exId;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(DescriptionVm.class)) {
                return (T)  new DescriptionVm(repo, exId);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
