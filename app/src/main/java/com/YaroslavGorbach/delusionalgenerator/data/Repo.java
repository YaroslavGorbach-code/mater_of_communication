package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface Repo {
    List<Exercise> getExercises();
    Single<Exercise> getExercise(Exercise.Name name);
    List<String> getWords(WordType type, Resources resources);
    Observable<Statistics> getStatistics(Exercise.Name name);
    Observable<Statistics> getStatisticsNext(Exercise.Name name, List<InputData> currentData);
    Observable<Statistics> getStatisticsPrevious(Exercise.Name name, List<InputData> currentData);
    void addStatistics(Statistics statistics);
    Single<List<Record>> getRecordsFromFile(Context context);

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
