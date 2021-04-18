package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.content.res.Resources;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingEx;
import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;

public class SpeakingVm extends ViewModel {
    public SpeakingEx speakingEx;

    SpeakingVm(
            int exId,
            Repo repo,
            StatisticsManager statisticsManager,
            Resources resources,
            Chronometer chronometer,
            Chronometer chronometerOneWord
    ) {
        speakingEx = new SpeakingExImp(
                repo.getExercises().get(exId),
                repo,
                statisticsManager,
                resources,
                chronometer,
                chronometerOneWord);
    }

    @Override
    protected void onCleared() {
        speakingEx.saveStatistics();
        super.onCleared();
    }

    public static class SpeakingVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final int exId;
        private final Resources resources;
        private final Chronometer chronometer;
        private final Chronometer chronometerOneWord;
        private final StatisticsManager statisticsManager;

        public SpeakingVmFactory(
                int exId,
                Repo repo,
                Resources resources,
                Chronometer chronometer,
                Chronometer chronometerOneWord,
                StatisticsManager statisticsManager
        ) {
            super();
            this.repo = repo;
            this.exId = exId;
            this.resources = resources;
            this.chronometer = chronometer;
            this.chronometerOneWord = chronometerOneWord;
            this.statisticsManager = statisticsManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SpeakingVm.class)) {
                return (T) new SpeakingVm(exId, repo, statisticsManager, resources, chronometer, chronometerOneWord);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
