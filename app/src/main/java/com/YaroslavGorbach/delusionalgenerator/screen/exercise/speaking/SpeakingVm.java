package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingEx;
import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingExImp;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.Chronometer;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorder;

public class SpeakingVm extends ViewModel {
    public SpeakingEx speakingEx;

    SpeakingVm(
            ExModel.Name name,
            Repo repo,
            StatisticsManager statisticsManager,
            Resources resources,
            Chronometer chronometer,
            Chronometer chronometerOneWord,
            VoiceRecorder voiceRecorder
    ) {
        speakingEx = new SpeakingExImp(
                repo.getExercise(name),
                repo,
                statisticsManager,
                resources,
                chronometer,
                chronometerOneWord,
                voiceRecorder);
    }

    @Override
    protected void onCleared() {
        speakingEx.saveStatistics();
        super.onCleared();
    }

    public static class SpeakingVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final ExModel.Name name;
        private final Resources resources;
        private final Chronometer chronometer;
        private final Chronometer chronometerOneWord;
        private final StatisticsManager statisticsManager;
        private final VoiceRecorder voiceRecorder;

        public SpeakingVmFactory(
                ExModel.Name name,
                Repo repo,
                Resources resources,
                Chronometer chronometer,
                Chronometer chronometerOneWord,
                StatisticsManager statisticsManager,
                VoiceRecorder voiceRecorder
        ) {
            super();
            this.repo = repo;
            this.name = name;
            this.resources = resources;
            this.chronometer = chronometer;
            this.chronometerOneWord = chronometerOneWord;
            this.statisticsManager = statisticsManager;
            this.voiceRecorder = voiceRecorder;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SpeakingVm.class)) {
                return (T) new SpeakingVm(name, repo, statisticsManager, resources,
                        chronometer, chronometerOneWord, voiceRecorder);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
