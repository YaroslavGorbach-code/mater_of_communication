package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.SpeakingEx;
import com.YaroslavGorbach.delusionalgenerator.component.SpeakingExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class SpeakingVm extends ViewModel {
    public SpeakingEx speakingEx;

    SpeakingVm(int exId, Repo repo, Resources resources) {
        speakingEx = new SpeakingExImp(repo.getExercises().get(exId), repo, resources);
    }

    public static class SpeakingVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final int exId;
        private final Resources resources;


        public SpeakingVmFactory(int exId, Repo repo, Resources resources) {
            super();
            this.repo = repo;
            this.exId = exId;
            this.resources = resources;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SpeakingVm.class)) {
                return (T) new SpeakingVm(exId, repo, resources);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
