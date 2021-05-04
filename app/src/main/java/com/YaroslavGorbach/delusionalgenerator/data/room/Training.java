package com.YaroslavGorbach.delusionalgenerator.data.room;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;

import java.util.ArrayList;
import java.util.Date;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

@Entity
public class Training {
    @PrimaryKey
    public Date date;
    public int number;
    public int days;
    public ArrayList<Exercise> exercises;
    @Ignore
    private int progress;


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
                        if (getIsOver())return 100;
                        this.progress = this.progress + progress/exercises.size();
                        return this.progress;
                    }).blockingLast(0);
        }else {
            return 0;
        }
    }

    public boolean getIsOver(){
        return !Observable.fromIterable(exercises).any(exercise -> exercise.done != exercise.aim).blockingGet();
    }

}
