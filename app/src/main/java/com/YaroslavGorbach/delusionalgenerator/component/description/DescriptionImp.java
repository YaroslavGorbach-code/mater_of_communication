package com.YaroslavGorbach.delusionalgenerator.component.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.Statistics;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.util.ArrayList;
import java.util.Collections;
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
        List<InputData> data = new ArrayList<>();
        for (Statistics s: mRepo.getStatisticsLast(mExName)){
            data.add(new InputData(s.value, s.dataTime));
        }
        Collections.reverse(data);
        mStatisticsData.postValue(data);
        return mStatisticsData;
    }

    @Override
    public void onStatisticsNext() {
        List<InputData> data = new ArrayList<>();
        for (Statistics s: mRepo.getStatisticsNext(mExName)){
            data.add(new InputData(s.value, s.dataTime));
        }
        Collections.reverse(data);
        if (!data.isEmpty())
             mStatisticsData.postValue(data);
    }

    @Override
    public void onStatisticsPrevious() {
        List<InputData> data = new ArrayList<>();
        for (Statistics s: mRepo.getStatisticsPrevious(mExName)){
            data.add(new InputData(s.value, s.dataTime));
        }
        Collections.reverse(data);
        if (!data.isEmpty())
            mStatisticsData.postValue(data);
    }
}
