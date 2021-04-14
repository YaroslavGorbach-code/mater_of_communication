package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.content.res.Resources;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.speaking.SpeakingEx;
import com.YaroslavGorbach.delusionalgenerator.component.speaking.SpeakingExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class SpeakingVm extends ViewModel {
    public SpeakingEx speakingEx;

    SpeakingVm(
            int exId,
            Repo repo,
            Resources resources,
            Chronometer chronometer,
            Chronometer chronometerOneWord,
            String recordPath
    ) {
        speakingEx = new SpeakingExImp(repo.getExercises().get(exId), repo,
                resources, chronometer, chronometerOneWord, recordPath);
    }

    public static class SpeakingVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final int exId;
        private final Resources resources;
        private final Chronometer chronometer;
        private final Chronometer chronometerOneWord;
        private final String recordPath;


        public SpeakingVmFactory(
                int exId,
                Repo repo,
                Resources resources,
                Chronometer chronometer,
                Chronometer chronometerOneWord,
                String recordPath
        ) {
            super();
            this.repo = repo;
            this.exId = exId;
            this.resources = resources;
            this.chronometer = chronometer;
            this.chronometerOneWord = chronometerOneWord;
            this.recordPath = recordPath;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SpeakingVm.class)) {
                return (T) new SpeakingVm(exId, repo, resources, chronometer, chronometerOneWord, recordPath);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
