package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.io.File;
import java.util.List;

public interface Repo {
    List<ExModel> getExercises();
    ExModel getExercise(ExModel.Name name);
    List<String> getWords(WordType type, Resources resources);
    List<Statistics> getStatisticsLast(ExModel.Name name);
    List<Statistics> getStatisticsNext(ExModel.Name name);
    List<Statistics> getStatisticsPrevious(ExModel.Name name);
    void addStatistics(Statistics statistics);
    File[] getRecords(Context context);

    class RepoProvider{
        public RepoImp provideRepo(Context context){
            Database database = Database.getInstance(context);
            return new RepoImp(database);
        }
    }
    enum WordType {
        ALIVE,
        NOT_ALIVE,
        ABBREVIATION,
        FILLING,
        LETTER,
        PROFESSIONS,
        TERMS,
        EASY_T_T,
        DIFFICULT_T_T,
        VERY_DIFFICULT_T_T
    }

}
