package com.YaroslavGorbach.delusionalgenerator.component.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;
import java.util.List;

public class DescriptionImp implements Description {
    private final Repo mRepo;
    private final Exercise mExercise;
    private final MutableLiveData<List<InputData>> mStatisticsData = new MutableLiveData<>();

    public DescriptionImp(Repo repo, Exercise.Name name){
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
    public LiveData<List<InputData>> getStatistics() {
        mRepo.getStatistics(mExercise.getName())
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
        return mStatisticsData;
    }

    @Override
    public void onStatisticsNext() {
         mRepo.getStatisticsNext(mExercise.getName(), mStatisticsData.getValue())
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
    }

    @Override
    public void onStatisticsPrevious() {
         mRepo.getStatisticsPrevious(mExercise.getName(), mStatisticsData.getValue())
                 .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                 .toList()
                 .subscribe(mStatisticsData::postValue).dispose();
    }
}
