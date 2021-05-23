package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.data.domain.ChartInputData;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Record;
import com.YaroslavGorbach.delusionalgenerator.data.local.inmemory.InMemoryDb;
import com.YaroslavGorbach.delusionalgenerator.data.local.RoomDb;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Statistics;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;
import com.YaroslavGorbach.delusionalgenerator.data.local.SharedPrefStorage;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.util.TimeAndDataUtil;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepoImp implements Repo {
    private final RoomDb mRoomDb;
    private final InMemoryDb mInMemoryDb;
    private final SharedPrefStorage mSharedPrefStorage;

    public RepoImp(RoomDb roomDb, InMemoryDb inMemoryDb, SharedPrefStorage storage) {
        mRoomDb = roomDb;
        mInMemoryDb = inMemoryDb;
        mSharedPrefStorage = storage;
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
            if (TimeAndDataUtil.isNewTrainingAllow(training.date, currentTime)) {
                if (!training.getIsOver()) training.days = 0;
                Training trainingNew = new Training(
                        currentTime,
                        training.days,
                        training.number,
                        Training.generateTrainingExs(training, this));
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
        if (training.getIsOver()) {
            training.days++;
            training.number++;
        }
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

    @Override
    public boolean getFirstOpen() {
        return mSharedPrefStorage.getFirstOpen();
    }

    @Override
    public void setFirstOpen(boolean firstOpen) {
        mSharedPrefStorage.setFirstOpen(firstOpen);
    }

    @Override
    public Calendar getNotificationCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, mSharedPrefStorage.getNotificationHour());
        calendar.set(Calendar.MINUTE, mSharedPrefStorage.getNotificationMinute());
        return calendar;
    }

    @Override
    public void setNotificationCalendar(Calendar calendar) {
        mSharedPrefStorage.setNotificationHour(calendar.get(Calendar.HOUR_OF_DAY));
        mSharedPrefStorage.setNotificationMinute(calendar.get(Calendar.MINUTE));
    }

    @Override
    public String getNotificationText() {
        return mSharedPrefStorage.getNotificationText();
    }

    @Override
    public void setNotificationText(String text) {
        mSharedPrefStorage.setNotificationText(text);
    }

    @Override
    public boolean getNotificationIsAllow() {
        return mSharedPrefStorage.getNotificationIsAllow();
    }

    @Override
    public void setNotificationIsAllow(boolean isEnable) {
        mSharedPrefStorage.setNotificationIsAllow(isEnable);
    }

    @Override
    public boolean interstitialAdIsAllow() {
        return mSharedPrefStorage.getInterstitialAdCount() >= AdManager.INTERSTITIAL_SHOW_LIMIT;
    }

    @Override
    public void incInterstitialAdCount() {
        if (mSharedPrefStorage.getInterstitialAdCount() < AdManager.INTERSTITIAL_SHOW_LIMIT) {
            mSharedPrefStorage.setInterstitialAdCount(mSharedPrefStorage.getInterstitialAdCount() + 1);
        } else {
            mSharedPrefStorage.setInterstitialAdCount(0);
        }
    }

    @Override
    public boolean getNightMod() {
        return mSharedPrefStorage.getNightMod();
    }

    @Override
    public void setNightMod(boolean nightMod) {
        mSharedPrefStorage.setNightMod(nightMod);
    }

    @Override
    public boolean getAdIsAllow() {
        return mSharedPrefStorage.getAdIsAllow();
    }

    @Override
    public void setAdIsAllow(boolean isAllow) {
        mSharedPrefStorage.setAdIsAllow(isAllow);
    }

    @Override
    public boolean isAscAppReviewAllow() {
        return getTraining().blockingFirst().days > 1;
    }


    @Override
    public Observable<ChartInputData> getChartData(Exercise.Name name) {
        ChartInputData inputData = new ChartInputData();
        return Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                .takeLast(ChartInputData.MAX_ITEMS_COUNT)
                .flatMap((Function<Statistics, ObservableSource<ChartInputData>>) statistics -> {
                    inputData.addValue(statistics.value);
                    inputData.addTime(statistics.time);
                    inputData.addLabel(TimeAndDataUtil.formatDD(statistics.time));
                    return Observable.just(inputData);
                }).defaultIfEmpty(inputData);
    }

    @Override
    public Observable<ChartInputData> getNextChartData(Exercise.Name name, ChartInputData currentData) {
        ChartInputData inputData = new ChartInputData();
        return Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                .filter(statistics -> statistics.time > currentData.getTime().get(currentData.getTime().size() - 1))
                .take(ChartInputData.MAX_ITEMS_COUNT)
                .switchIfEmpty(Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name)).takeLast(ChartInputData.MAX_ITEMS_COUNT))
                .flatMap((Function<Statistics, ObservableSource<ChartInputData>>) statistics -> {
                    inputData.addValue(statistics.value);
                    inputData.addTime(statistics.time);
                    inputData.addLabel(TimeAndDataUtil.formatDD(statistics.time));
                    return Observable.just(inputData);
                }).defaultIfEmpty(inputData);
    }

    @Override
    public Observable<ChartInputData> getPreviousChartData(Exercise.Name name, ChartInputData currentData) {
        ChartInputData inputData = new ChartInputData();
        return Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                .filter(statistics -> statistics.time < currentData.getTime().get(0))
                .takeLast(ChartInputData.MAX_ITEMS_COUNT)
                .switchIfEmpty(Observable.fromIterable(mRoomDb.statisticsDao().getStatistics(name))
                        .takeLast(ChartInputData.MAX_ITEMS_COUNT))
                 .flatMap((Function<Statistics, ObservableSource<ChartInputData>>) statistics -> {
                     inputData.addValue(statistics.value);
                     inputData.addTime(statistics.time);
                     inputData.addLabel(TimeAndDataUtil.formatDD(statistics.time));
                     return Observable.just(inputData);
                 }).defaultIfEmpty(inputData);
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
