package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.component.vocabulary.Vocabulary;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerVocabularyComponent;
import com.YaroslavGorbach.delusionalgenerator.di.VocabularyComponent;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;

public class VocabularyVm extends AndroidViewModel {
  private VocabularyComponent vocabularyComponent;

    public VocabularyVm(@NonNull Application application) {
        super(application);
    }

    public VocabularyComponent getVocabularyComponent(Exercise.Name name, Exercise.Type type) {
        if (vocabularyComponent==null){
            vocabularyComponent = DaggerVocabularyComponent.factory().create(name, type,  ((App)getApplication()).appComponent);
        }
        return vocabularyComponent;
    }

    //    @Override
//    protected void onCleared() {
//        vocabulary.saveStatistics();
//        super.onCleared();
//    }


}
