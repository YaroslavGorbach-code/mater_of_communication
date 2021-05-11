package com.YaroslavGorbach.delusionalgenerator.data.room;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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


    public Training(Date date, int days, int number, ArrayList<Exercise> exercises) {
        this.date = date;
        this.exercises = exercises;
        this.days = days;
        this.number = number;
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

    public static ArrayList<Exercise> generateTrainingExs(Training training, Repo repo) {
        Random random = new Random();
        Log.v("numberT", String.valueOf(training.number));
        ArrayList<Exercise> exercises = new ArrayList<>();
        if (training.number >= 10 && training.number < 20){
            exercises.add((repo.getExercise(Exercise.Name.DIFFICULT_TONGUE_TWISTERS)));
        }else if (training.number >= 20){
            exercises.add((repo.getExercise(Exercise.Name.VERY_DIFFICULT_TONGUE_TWISTERS)));
        }else {
            exercises.add((repo.getExercise(Exercise.Name.EASY_TONGUE_TWISTERS)));
        }

        switch (random.nextInt(4)){
            case 0:
                exercises.add((repo.getExercise(Exercise.Name.LINGUISTIC_PYRAMIDS)));
                exercises.add((repo.getExercise(Exercise.Name.WHAT_I_SEE_I_SING_ABOUT)));
                exercises.add((repo.getExercise(Exercise.Name.CO_AUTHORED_WITH_DAHL)));
                break;
            case 1:
                exercises.add((repo.getExercise(Exercise.Name.RAVEN_LOOK_LIKE_A_TABLE)));
                exercises.add((repo.getExercise(Exercise.Name.QUESTION_ANSWER)));
                exercises.add((repo.getExercise(Exercise.Name.RORSCHACH_TEST)));
                break;
            case 2:
                exercises.add((repo.getExercise(Exercise.Name.MAGIC_NAMING)));
                exercises.add((repo.getExercise(Exercise.Name.BUYING_SELLING)));
                exercises.add((repo.getExercise(Exercise.Name.ADVANCED_BINDING)));
                exercises.add(repo.getExercise(Exercise.Name.REMEMBER_ALL));
                break;
            case 3:
                exercises.add((repo.getExercise(Exercise.Name.OTHER_ABBREVIATIONS)));
                exercises.add((repo.getExercise(Exercise.Name.WILL_NOT_BE_WORSE)));
                exercises.add((repo.getExercise(Exercise.Name.RAVEN_LOOK_LIKE_A_TABLE_FILINGS)));
                exercises.add(repo.getExercise(Exercise.Name.BUYING_SELLING));
        }
        exercises.add((repo.getExercise(Exercise.Name.VERBS)));
        exercises.add((repo.getExercise(Exercise.Name.NOUNS)));
        exercises.add((repo.getExercise(Exercise.Name.ADJECTIVES)));

        for (Exercise e : exercises) {
            e.type = Exercise.Type.DAILY;
            switch (e.getName()){
                case LINGUISTIC_PYRAMIDS:
                case OTHER_ABBREVIATIONS:
                case MAGIC_NAMING:
                case RORSCHACH_TEST:
                    e.aim = 10;
                    break;
                case RAVEN_LOOK_LIKE_A_TABLE:
                case STORYTELLER_IMPROVISER:
                case ADVANCED_BINDING:
                case WHAT_I_SEE_I_SING_ABOUT:
                case BUYING_SELLING:
                case CO_AUTHORED_WITH_DAHL:
                case WILL_NOT_BE_WORSE:
                case QUESTION_ANSWER:
                case RAVEN_LOOK_LIKE_A_TABLE_FILINGS:
                    e.aim = 5;
                    break;
                case EASY_TONGUE_TWISTERS:
                case DIFFICULT_TONGUE_TWISTERS:
                case VERY_DIFFICULT_TONGUE_TWISTERS:
                    e.aim = 3;
                    break;
                case NOUNS:
                case ADJECTIVES:
                case VERBS:
                    e.aim = 1;
                    break;
                case REMEMBER_ALL:
                    e.aim = 15;
                    break;
            }
        }
        return exercises;
    }

}
