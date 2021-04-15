package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import java.io.File;
import java.util.List;

public interface Repo {
    List<ExModel> getExercises();
    List<String> getWords(WordType type, Resources resources);
    File[] getRecords(Context context);

    class RepoProvider{
        public RepoImp provideRepo(){
            return new RepoImp();
        }
    }
}
