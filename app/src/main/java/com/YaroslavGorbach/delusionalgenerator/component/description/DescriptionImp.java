package com.YaroslavGorbach.delusionalgenerator.component.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;
import java.util.List;

public class DescriptionImp implements Description {
    private final Repo mRepo;
    private Exercise mExercise;
    private final MutableLiveData<List<InputData>> mStatisticsData = new MutableLiveData<>();

    public DescriptionImp(Repo repo, Exercise.Name name){
        mRepo = repo;
        repo.getExercise(name).subscribe(exModel -> mExercise = exModel).dispose();
    }

    @Override
    public int getDescriptionId() {
        return mExercise.descriptionId;
    }

    @Override
    public int getImageId() {
        return mExercise.imageId;
    }

    @Override
    public Exercise.Category getCategory() {
        return mExercise.category;
    }

    @Override
    public LiveData<List<InputData>> getStatistics() {
        mRepo.getStatistics(mExercise.name)
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
        return mStatisticsData;
    }

    @Override
    public void onStatisticsNext() {
         mRepo.getStatisticsNext(mExercise.name, mStatisticsData.getValue())
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
    }

    @Override
    public void onStatisticsPrevious() {
         mRepo.getStatisticsPrevious(mExercise.name, mStatisticsData.getValue())
                 .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                 .toList()
                 .subscribe(mStatisticsData::postValue).dispose();
    }
}
