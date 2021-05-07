package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.data.room.RoomDb;
import com.YaroslavGorbach.delusionalgenerator.data.room.Statistics;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;

import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface Repo {
    List<Exercise> getExercises();
    List<Exercise> getExercises(Exercise.Category category);
    Exercise getExercise(Exercise.Name name);
    List<String> getWords(WordType type, Resources resources);
    Observable<Statistics> getStatistics(Exercise.Name name);
    Observable<Statistics> getStatisticsNext(Exercise.Name name, ChartInputData currentData);
    Observable<Statistics> getStatisticsPrevious(Exercise.Name name, ChartInputData currentData);
    void addStatistics(Statistics statistics);
    void deleteRecord(Record record);
    Single<List<Record>> getRecords(Context context);
    Observable<Training> getTraining();
    void updateTrainingEx(Exercise exercise);
    int getTrainingExDone(Exercise exercise);
    int getTrainingExAim(Exercise exercise);
    boolean getFirstOpen();
    void setFirstOpen(boolean firstOpen);
    Calendar getNotificationCalendar();
    void setNotificationCalendar (Calendar calendar);
    String getNotificationText();
    void setNotificationText(String text);
    boolean getNotificationIsAllow();
    void setNotificationIsAllow(boolean isAllow);
    boolean interstitialAdIsAllow();
    void incInterstitialAdCount();

    class RepoProvider{
        public RepoImp provideRepo(Context context){
            RoomDb roomDb = RoomDb.getInstance(context);
            return new RepoImp(roomDb, new InMemoryDbImp(), new SharedPrefStorageImp(context));
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
