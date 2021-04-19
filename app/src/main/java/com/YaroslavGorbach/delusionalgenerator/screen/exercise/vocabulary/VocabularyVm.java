package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

public class VocabularyVm extends ViewModel {
    public final VocabularyEx vocabularyEx;

    VocabularyVm(Repo repo, int exId, Timer timer, StatisticsManager statisticsManager){
        vocabularyEx = new VocabularyExImp(repo.getExercise(exId),  timer, statisticsManager, repo);
    }

    @Override
    protected void onCleared() {
        vocabularyEx.saveStatistics();
        super.onCleared();
    }

    public static class VocabularyVmFactory  extends ViewModelProvider.NewInstanceFactory{
        private final int exId;
        private final Repo repo;
        private final Timer timer;
        private final StatisticsManager statisticsManager;


        public VocabularyVmFactory(Repo repo, int exId, Timer timer, StatisticsManager statisticsManager){
            super();
            this.exId = exId;
            this.repo = repo;
            this.timer = timer;
            this.statisticsManager = statisticsManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(VocabularyVm.class)) {
                return (T)  new VocabularyVm(repo, exId, timer, statisticsManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
