package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class VocabularyVm extends ViewModel {
    public final VocabularyEx vocabularyEx;

    VocabularyVm(Repo repo, int exId){
        vocabularyEx = new VocabularyExImp(repo.getExercises().get(exId));
    }

    public static class VocabularyVmFactory  extends ViewModelProvider.NewInstanceFactory{
        private final int exId;
        private final Repo repo;

        public VocabularyVmFactory(Repo repo, int exId){
            super();
            this.exId = exId;
            this.repo = repo;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(VocabularyVm.class)) {
                return (T)  new VocabularyVm(repo, exId);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
