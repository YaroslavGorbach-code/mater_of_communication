package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.vocabulary.Vocabulary;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;

public class VocabularyVm extends ViewModel {
    public final Vocabulary vocabulary;
    public final AdManager adManager;

    VocabularyVm(Vocabulary vocabulary, AdManager adManager){
        this.vocabulary = vocabulary;
        this.adManager = adManager;
    }

    @Override
    protected void onCleared() {
        vocabulary.saveStatistics();
        super.onCleared();
    }

    public static class VocabularyVmFactory  extends ViewModelProvider.NewInstanceFactory{
      private final Vocabulary vocabulary;
      private final AdManager adManager;

        public VocabularyVmFactory(Vocabulary vocabulary, AdManager adManager){
            super();
            this.vocabulary = vocabulary;
            this.adManager = adManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(VocabularyVm.class)) {
                return (T)  new VocabularyVm(vocabulary, adManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
