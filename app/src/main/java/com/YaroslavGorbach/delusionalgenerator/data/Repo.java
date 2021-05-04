package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.data.room.RoomDb;
import com.YaroslavGorbach.delusionalgenerator.data.room.Statistics;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface Repo {
    List<Exercise> getExercises();
    List<Exercise> getExercises(Exercise.Category category);
    Exercise getExercise(Exercise.Name name);
    List<String> getWords(WordType type, Resources resources);
    Observable<Statistics> getStatistics(Exercise.Name name);
    Observable<Statistics> getStatisticsNext(Exercise.Name name, List<InputData> currentData);
    Observable<Statistics> getStatisticsPrevious(Exercise.Name name, List<InputData> currentData);
    void addStatistics(Statistics statistics);
    void deleteRecord(Record record);
    Single<List<Record>> getRecords(Context context);
    Observable<Training> getTraining();
    void updateTrainingEx(Exercise exercise);
    int getTrainingExDone(Exercise exercise);
    int getTrainingExAim(Exercise exercise);


    class RepoProvider{
        public RepoImp provideRepo(Context context){
            RoomDb roomDb = RoomDb.getInstance(context);
            return new RepoImp(roomDb, new InMemoryDbImp());
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
        QUESTIONS,
        EASY_T_T,
        DIFFICULT_T_T,
        VERY_DIFFICULT_T_T
    }

}
