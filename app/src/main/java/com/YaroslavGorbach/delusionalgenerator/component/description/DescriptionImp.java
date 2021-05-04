package com.YaroslavGorbach.delusionalgenerator.component.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ChartInputData;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.util.TimeUtil;

public class DescriptionImp implements Description {
    private final Repo mRepo;
    private final Exercise mExercise;
    private final MutableLiveData<ChartInputData> mStatisticsData = new MutableLiveData<>();

    public DescriptionImp(Repo repo, Exercise.Name name) {
        mRepo = repo;
        mExercise = repo.getExercise(name);
    }

    @Override
    public int getDescriptionId() {
        return mExercise.getDescriptionId();
    }

    @Override
    public int getImageId() {
        return mExercise.getImageId();
    }

    @Override
    public Exercise.Category getCategory() {
        return mExercise.getCategory();
    }

    @Override
    public LiveData<ChartInputData> getChartData() {
        ChartInputData inputData = new ChartInputData();
        mRepo.getStatistics(mExercise.getName())
                .forEach(statistics -> {
                    inputData.addValue(statistics.value);
                    inputData.addTime(statistics.time);
                    inputData.addLabel(TimeUtil.formatDD(statistics.time));
                });
        mStatisticsData.setValue(inputData);
        return mStatisticsData;
    }


    @Override
    public void onStatisticsNext() {
        ChartInputData inputData = new ChartInputData();
        mRepo.getStatisticsNext(mExercise.getName(), mStatisticsData.getValue())
                 .forEach(statistics -> {
                     inputData.addValue(statistics.value);
                     inputData.addTime(statistics.time);
                     inputData.addLabel(TimeUtil.formatDD(statistics.time));
                 });
        mStatisticsData.setValue(inputData);
    }

    @Override
    public void onStatisticsPrevious() {
        ChartInputData inputData = new ChartInputData();
        mRepo.getStatisticsPrevious(mExercise.getName(), mStatisticsData.getValue())
                 .forEach(statistics -> {
                     inputData.addValue(statistics.value);
                     inputData.addTime(statistics.time);
                     inputData.addLabel(TimeUtil.formatDD(statistics.time));
                 });
        mStatisticsData.setValue(inputData);
    }
}
