package com.YaroslavGorbach.delusionalgenerator.data;
import android.content.Context;
import android.content.res.Resources;
import com.YaroslavGorbach.delusionalgenerator.data.room.RoomDb;
import com.YaroslavGorbach.delusionalgenerator.data.room.Statistics;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;
import com.YaroslavGorbach.delusionalgenerator.util.TimeUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepoImp implements Repo {
    private final RoomDb mRoomDb;
    private final InMemoryDb mInMemoryDb;

    public RepoImp(RoomDb roomDb, InMemoryDb inMemoryDb) {
        mRoomDb = roomDb;
        mInMemoryDb = inMemoryDb;
    }

    @Override
    public List<Exercise> getExercises() {
        return mInMemoryDb.getExercises();
    }

    @Override
    public List<Exercise> getExercises(Exercise.Category category) {
        return mInMemoryDb.getExercises(category);
    }

    @Override
    public Exercise getExercise(Exercise.Name name) {
        return mInMemoryDb.getExercise(name);
    }

    @Override
    public List<String> getWords(WordType type, Resources resources) {
       return mInMemoryDb.getWords(type, resources);
    }

    @Override
    public Single<List<Record>> getRecords(Context context) {
        File files = new File(context.getExternalFilesDir("/").getAbsolutePath());
        return Observable.fromArray(files.listFiles())
                .map(Record::new)
                .toSortedList((o1, o2) -> {
                    if (o1.getLastModified() < o2.getLastModified()) return 1;
                    else return 0;
                });
    }

    @Override
    public Observable<Training> getTraining() {
        Date currentTime = new Date();
        return mRoomDb.dailyTrainingDao().getDailyTraining().map(training -> {
            if (TimeUtil.getDaysBetween(training.date.getTime(), currentTime.getTime()) >= 1) {
                if (TimeUtil.getDaysBetween(training.date.getTime(), currentTime.getTime()) > 1 || !training.getIsOver())
                    training.days = 0;
                Training trainingNew = new Training(
                        currentTime,
                        training.days,
                        generateTrainingExs(training));
                mRoomDb.dailyTrainingDao().insert(trainingNew);
                return trainingNew;
            } else {
                return training;
            }
        });
    }

    @Override
    public void updateTrainingEx(Exercise exercise) {
        Training training = getTraining().blockingFirst();
        List<Exercise> newList = Observable.fromIterable(training.exercises).map(exNew -> {
            if (exercise.getName() == exNew.getName()) {
                exNew.done = exercise.done;
            }
            return exNew;
        }).toList().blockingGet();
        training.exercises.clear();
        training.exercises.addAll(newList);
        if (training.getIsOver()) { training.days++; training.number++; }
        mRoomDb.dailyTrainingDao().insert(training);
    }


    @Override
    public int getTrainingExDone(Exercise exercise) {
        Training training = getTraining().blockingFirst();
        return Observable.fromIterable(training.exercises).filter(exercise1 ->
                exercise1.getName() == exercise.getName()).blockingFirst().done;
    }

    @Override
    public int getTrainingExAim(Exercise exercise) {
        Training training = getTraining().blockingFirst();
        return Observable.fromIterable(training.exercises).filter(exercise1 ->
                exercise1.getName() == exercise.getName()).blockingFirst().aim;
    }

    private ArrayList<Exercise> generateTrainingExs(Training training) {
        Random random = new Random();
        ArrayList<Exercise> exercises = new ArrayList<>();
        if (training.number < 10){
            exercises.add((getExercise(Exercise.Name.EASY_TONGUE_TWISTERS)));
        }
        if (training.number > 10 && training.number < 20){
            exercises.add((getExercise(Exercise.Name.DIFFICULT_TONGUE_TWISTERS)));
        }
        if (training.number > 20){
            exercises.add((getExercise(Exercise.Name.VERY_DIFFICULT_TONGUE_TWISTERS)));
        }

        switch (random.nextInt(3)){
            case 0:
                exercises.add((getExercise(Exercise.Name.LINGUISTIC_PYRAMIDS)));
                exercises.add((getExercise(Exercise.Name.WHAT_I_SEE_I_SING_ABOUT)));
                exercises.add((getExercise(Exercise.Name.CO_AUTHORED_WITH_DAHL)));
                break;
            case 1:
                exercises.add((getExercise(Exercise.Name.RAVEN_LOOK_LIKE_A_TABLE)));
                exercises.add((getExercise(Exercise.Name.QUESTION_ANSWER)));
                exercises.add((getExercise(Exercise.Name.RORSCHACH_TEST)));
                break;
            case 2:
                exercises.add((getExercise(Exercise.Name.MAGIC_NAMING)));
                exercises.add((getExercise(Exercise.Name.BUYING_SELLING)));
                exercises.add((getExercise(Exercise.Name.ADVANCED_BINDING)));
                exercises.add(getExercise(Exercise.Name.REMEMBER_ALL));
                break;
            case 3:
                exercises.add((getExercise(Exercise.Name.OTHER_ABBREVIATIONS)));
                exercises.add((getExercise(Exercise.Name.WILL_NOT_BE_WORSE)));
                exercises.add((getExercise(Exercise.Name.RAVEN_LOOK_LIKE_A_TABLE_FILINGS)));
                exercises.add(getExercise(Exercise.Name.BUYING_SELLING));
        }
        exercises.add((getExercise(Exercise.Name.VERBS)));
        exercises.add((getExercise(Exercise.Name.NOUNS)));
        exercises.add((getExercise(Exercise.Name.ADJECTIVES)));

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

    @Override
    public Observable<Statistics> getStatistics(Exercise.Name name) {
        return Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                .takeLast(8);
    }

    @Override
    public Observable<Statistics> getStatisticsNext(Exercise.Name name, ChartInputData currentData) {
        return Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                .filter(statistics -> statistics.time > currentData.getTime().get(currentData.getTime().size() - 1))
                .take(8)
                .switchIfEmpty(Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                        .takeLast(8));
    }

    @Override
    public Observable<Statistics> getStatisticsPrevious(Exercise.Name name, ChartInputData currentData) {
        return Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                .filter(statistics -> statistics.time < currentData.getTime().get(0))
                .takeLast(8)
                .switchIfEmpty(Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                .takeLast(8));
    }

    @Override
    public void addStatistics(Statistics statistics) {
        Completable.create(emitter -> mRoomDb.statisticsDao().insert(statistics))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void deleteRecord(Record record) {
        record.getFile().delete();
    }

}
