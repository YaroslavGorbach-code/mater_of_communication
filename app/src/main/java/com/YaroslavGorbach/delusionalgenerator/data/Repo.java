package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.data.domain.ChartInputData;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Record;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Statistics;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface Repo {
    List<Exercise> getExercises();
    List<Exercise> getExercises(Exercise.Category category);
    Exercise getExercise(Exercise.Name name);
    List<String> getWords(WordType type, Resources resources);
    Observable<ChartInputData> getChartData(Exercise.Name name);
    Observable<ChartInputData> getNextChartData(Exercise.Name name, ChartInputData currentData);
    Observable<ChartInputData> getPreviousChartData(Exercise.Name name, ChartInputData currentData);
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
    boolean getNightMod();
    void setNightMod(boolean nightMod);
    boolean getAdIsAllow();
    void setAdIsAllow(boolean isAllow);
    boolean isAscAppReviewAllow();
    void setDateLastReviewAsc(Date date);

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
