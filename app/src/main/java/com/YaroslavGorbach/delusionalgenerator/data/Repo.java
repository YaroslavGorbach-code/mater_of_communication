package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.io.File;
import java.util.List;

public interface Repo {
    List<ExModel> getExercises();
    List<String> getWords(WordType type, Resources resources);
    List<Statistics> getStatistics(int exId);
    void addStatistics(Statistics statistics);
    File[] getRecords(Context context);

    class RepoProvider{
        public RepoImp provideRepo(Context context){
            Database database = Database.getInstance(context);
            return new RepoImp(database);
        }
    }
}
