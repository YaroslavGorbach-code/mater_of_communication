package com.YaroslavGorbach.delusionalgenerator.data;

import android.util.Log;
import android.util.Pair;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

@Entity
public class Training {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public Date date;
    @Ignore
    private int progress;
    public int days;
    public ArrayList<Exercise> exercises;

    public Training(Date date, int days, ArrayList<Exercise> exercises) {
        this.date = date;
        this.exercises = exercises;
        this.days = days;
    }

    public Integer getProgress() {
        if (exercises !=null) {
            return Observable.fromIterable(exercises)
                    .flatMap((Function<Exercise, ObservableSource<Integer>>) exercise -> Observable.just(exercise.getProgress()))
                    .map(progress -> {
                        this.progress = this.progress + progress/exercises.size();
                        return this.progress;
                    })
                    .map(progress ->{
                        if (progress == 99) return 100;
                        else return progress;
                    }).blockingLast(0);
        }else {
            return 0;
        }
    }

    public boolean getIsOver(){
        return !Observable.fromIterable(exercises).any(exercise -> exercise.done != exercise.aim).blockingGet();
    }
}
