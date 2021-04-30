package com.YaroslavGorbach.delusionalgenerator.screen.nav;

import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;

public interface Navigation {
    void openSpeakingEx(Exercise.Name name, Exercise.Type type);
    void openVocabularyEx(Exercise.Name name, Exercise.Type type);
    void openDescription(Exercise.Name name, Exercise.Type type);
    void showFinishDialog(VocabularyEx.Result result);
    void openTraining();
    void up();
}
