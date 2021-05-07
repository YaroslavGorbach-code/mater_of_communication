package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManager;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.Timer;

public class VocabularyVm extends ViewModel {
    public final VocabularyEx vocabularyEx;
    public final AdManager adManager;

    VocabularyVm(Repo repo, Exercise.Name name, Exercise.Type type, Timer timer, StatisticsManager statisticsManager){
        vocabularyEx = new VocabularyExImp(name, type, timer, statisticsManager, repo);
        adManager = new AdManager(repo);
    }

    @Override
    protected void onCleared() {
        vocabularyEx.saveStatistics();
        super.onCleared();
    }

    public static class VocabularyVmFactory  extends ViewModelProvider.NewInstanceFactory{
        private final Exercise.Name name;
        private final Exercise.Type type;
        private final Repo repo;
        private final Timer timer;
        private final StatisticsManager statisticsManager;


        public VocabularyVmFactory(Repo repo, Exercise.Name name, Exercise.Type type, Timer timer, StatisticsManager statisticsManager){
            super();
            this.name = name;
            this.type = type;
            this.repo = repo;
            this.timer = timer;
            this.statisticsManager = statisticsManager;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(VocabularyVm.class)) {
                return (T)  new VocabularyVm(repo, name, type, timer, statisticsManager);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

}
