package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyExImp;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

public class VocabularyVm extends ViewModel {
    public final VocabularyEx vocabularyEx;

    VocabularyVm(Repo repo, ExModel.Name name, Timer timer, StatisticsManager statisticsManager){
        vocabularyEx = new VocabularyExImp(repo.getExercise(name),  timer, statisticsManager, repo);
    }

    @Override
    protected void onCleared() {
        vocabularyEx.saveStatistics();
        super.onCleared();
    }

    public static class VocabularyVmFactory  extends ViewModelProvider.NewInstanceFactory{
        private final ExModel.Name name;
        private final Repo repo;
        private final Timer timer;
        private final StatisticsManager statisticsManager;


        public VocabularyVmFactory(Repo repo, ExModel.Name name, Timer timer, StatisticsManager statisticsManager){
            super();
            this.name = name;
            this.repo = repo;
            this.timer = timer;
            this.statisticsManager = statisticsManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(VocabularyVm.class)) {
                return (T)  new VocabularyVm(repo, name, timer, statisticsManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
