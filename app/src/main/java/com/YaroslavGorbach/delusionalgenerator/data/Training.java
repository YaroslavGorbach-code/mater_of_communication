package com.YaroslavGorbach.delusionalgenerator.data;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

@Entity
public class Training {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long date;
    @Ignore
    private int progress;
    public int days;
    public ArrayList<Exercise> exercises;

    public Training(long date, int days, ArrayList<Exercise> exercises) {
        this.date = date;
        this.exercises = exercises;
        this.days = days;
    }

    public Integer getProgress() {
       return Observable.fromIterable(exercises)
               .flatMap((Function<Exercise, ObservableSource<Integer>>) exercise -> Observable.just(exercise.getProgress()))
               .map(progress -> this.progress = this.progress + progress/exercises.size())
                .map(progress ->{
                    Log.v("progress", "" + progress);
                    if (progress > 97) return 100;
                    else return progress;
                })
               .blockingLast(0);
    }
}
