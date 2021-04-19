package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.io.File;
import java.util.List;

public interface Repo {
    List<ExModel> getExercises();
    ExModel getExercise(int id);
    List<String> getWords(WordType type, Resources resources);
    List<Statistics> getStatisticsLast(int exId);
    List<Statistics> getStatisticsNext(int exId);
    List<Statistics> getStatisticsPrevious(int exId);

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
