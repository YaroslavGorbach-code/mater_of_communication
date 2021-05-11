package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingEx;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;

public class SpeakingVm extends ViewModel {
    public SpeakingEx speakingEx;
    public AdManager adManager;

    SpeakingVm(SpeakingEx speakingEx, AdManager adManager) {
        this.speakingEx = speakingEx;
        this.adManager = adManager;
    }

    @Override
    protected void onCleared() {
        speakingEx.saveStatistics();
        speakingEx.stopRecording();
        super.onCleared();
    }

    public static class SpeakingVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final SpeakingEx speakingEx;
        private final AdManager adManager;

        public SpeakingVmFactory(
                SpeakingEx speakingEx,
                AdManager adManager
        ) {
            super();
            this.speakingEx = speakingEx;
            this.adManager = adManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SpeakingVm.class)) {
                return (T) new SpeakingVm(speakingEx, adManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
