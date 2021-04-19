package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

public class VocabularyVm extends ViewModel {
    public final VocabularyEx vocabularyEx;

    VocabularyVm(Repo repo, int exId, Timer timer){
        vocabularyEx = new VocabularyExImp(repo.getExercise(exId), timer);
    }

    public static class VocabularyVmFactory  extends ViewModelProvider.NewInstanceFactory{
        private final int exId;
        private final Repo repo;
        private final Timer timer;

        public VocabularyVmFactory(Repo repo, int exId, Timer timer){
            super();
            this.exId = exId;
            this.repo = repo;
            this.timer = timer;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(VocabularyVm.class)) {
                return (T)  new VocabularyVm(repo, exId, timer);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
