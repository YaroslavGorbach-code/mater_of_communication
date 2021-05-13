package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.YaroslavGorbach.delusionalgenerator.App;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerVocabularyComponent;
import com.YaroslavGorbach.delusionalgenerator.di.VocabularyComponent;

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
