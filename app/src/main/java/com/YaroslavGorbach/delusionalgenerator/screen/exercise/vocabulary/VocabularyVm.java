package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

public class VocabularyVm extends ViewModel {
    public final VocabularyEx vocabularyEx;
    public final AdManager adManager;

    VocabularyVm(VocabularyEx vocabularyEx, AdManager adManager){
        this.vocabularyEx = vocabularyEx;
        this.adManager = adManager;
    }

    @Override
    protected void onCleared() {
        vocabularyEx.saveStatistics();
        super.onCleared();
    }

    public static class VocabularyVmFactory  extends ViewModelProvider.NewInstanceFactory{
      private final VocabularyEx vocabularyEx;
      private final AdManager adManager;

        public VocabularyVmFactory(VocabularyEx vocabularyEx, AdManager adManager){
            super();
            this.vocabularyEx = vocabularyEx;
            this.adManager = adManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(VocabularyVm.class)) {
                return (T)  new VocabularyVm(vocabularyEx, adManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
