package com.YaroslavGorbach.delusionalgenerator.component.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;
import java.util.List;


public class DescriptionImp implements Description {
    private final Repo mRepo;
    private final ExModel.Name mExName;
    private final MutableLiveData<List<InputData>> mStatisticsData = new MutableLiveData<>();

    public DescriptionImp(Repo repo, ExModel.Name name){
        mRepo = repo;
        mExName = name;
    }

    @Override
    public int getDescriptionId() {
        return mRepo.getExercise(mExName).descriptionId;
    }

    @Override
    public int getImageId() {
        return mRepo.getExercise(mExName).imageId;
    }

    @Override
    public ExModel.Category getCategory() {
        return mRepo.getExercise(mExName).category;
    }

    @Override
    public LiveData<List<InputData>> getStatistics() {
        mRepo.getStatistics(mExName)
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
        return mStatisticsData;
    }

    @Override
    public void onStatisticsNext() {
         mRepo.getStatisticsNext(mExName, mStatisticsData.getValue())
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
    }

    @Override
    public void onStatisticsPrevious() {
         mRepo.getStatisticsPrevious(mExName, mStatisticsData.getValue())
                 .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                 .toList()
                 .subscribe(mStatisticsData::postValue).dispose();
    }
}
