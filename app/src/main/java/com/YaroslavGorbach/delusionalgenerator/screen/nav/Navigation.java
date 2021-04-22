package com.YaroslavGorbach.delusionalgenerator.screen.nav;

import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;

public interface Navigation {
    void openSpeakingEx(ExModel.Name name);
    void openVocabularyEx(ExModel.Name name);
    void openDescription(ExModel.Name name);
    void showFinishDialog(VocabularyEx.Result result);
    void up();
}
