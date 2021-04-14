package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.res.Resources;

import java.util.List;

public interface Repo {
    List<ExModel> getExercises();
    List<String> getWords(WordType type, Resources resources);

    class RepoProvider{
        public RepoImp provideRepo(){
            return new RepoImp();
        }
    }
}
