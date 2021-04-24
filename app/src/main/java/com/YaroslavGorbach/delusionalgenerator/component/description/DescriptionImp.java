package com.YaroslavGorbach.delusionalgenerator.component.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;
import java.util.List;


public class DescriptionImp implements Description {
    private final Repo mRepo;
    private ExModel mExModel;
    private final MutableLiveData<List<InputData>> mStatisticsData = new MutableLiveData<>();

    public DescriptionImp(Repo repo, ExModel.Name name){
        mRepo = repo;
        repo.getExercise(name).subscribe(exModel -> mExModel = exModel).dispose();
    }

    @Override
    public int getDescriptionId() {
        return mExModel.descriptionId;
    }

    @Override
    public int getImageId() {
        return mExModel.imageId;
    }

    @Override
    public ExModel.Category getCategory() {
        return mExModel.category;
    }

    @Override
    public LiveData<List<InputData>> getStatistics() {
        mRepo.getStatistics(mExModel.name)
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
        return mStatisticsData;
    }

    @Override
    public void onStatisticsNext() {
         mRepo.getStatisticsNext(mExModel.name, mStatisticsData.getValue())
                .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                .toList()
                .subscribe(mStatisticsData::postValue).dispose();
    }

    @Override
    public void onStatisticsPrevious() {
         mRepo.getStatisticsPrevious(mExModel.name, mStatisticsData.getValue())
                 .map(statistics -> new InputData(statistics.value, statistics.dataTime))
                 .toList()
                 .subscribe(mStatisticsData::postValue).dispose();
    }
}
