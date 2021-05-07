package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingEx;
import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorder;

public class SpeakingVm extends ViewModel {
    public SpeakingEx speakingEx;
    public AdManager adManager;

    SpeakingVm(
            Exercise.Name name,
            Exercise.Type type,
            Repo repo,
            StatisticsManager statisticsManager,
            Resources resources,
            VoiceRecorder voiceRecorder
    ) {
        speakingEx = new SpeakingExImp(
                name,
                type,
                repo,
                statisticsManager,
                resources,
                voiceRecorder);
        adManager = new AdManager(repo);
    }

    @Override
    protected void onCleared() {
        speakingEx.saveStatistics();
        speakingEx.stopRecording();
        super.onCleared();
    }

    public static class SpeakingVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final Exercise.Type type;
        private final Exercise.Name name;
        private final Resources resources;
        private final StatisticsManager statisticsManager;
        private final VoiceRecorder voiceRecorder;

        public SpeakingVmFactory(
                Exercise.Name name,
                Exercise.Type type,
                Repo repo,
                Resources resources,
                StatisticsManager statisticsManager,
                VoiceRecorder voiceRecorder
        ) {
            super();
            this.repo = repo;
            this.name = name;
            this.type = type;
            this.resources = resources;
            this.statisticsManager = statisticsManager;
            this.voiceRecorder = voiceRecorder;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SpeakingVm.class)) {
                return (T) new SpeakingVm(name, type, repo, statisticsManager, resources, voiceRecorder);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
