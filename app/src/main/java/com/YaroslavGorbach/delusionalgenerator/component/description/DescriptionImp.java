package com.YaroslavGorbach.delusionalgenerator.component.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ChartInputData;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

import io.reactivex.rxjava3.functions.Consumer;

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
        mRepo.getChartData(mExercise.getName())
                .subscribe(mStatisticsData::setValue);
        return mStatisticsData;
    }


    @Override
    public void onChartNext() {
        mRepo.getNextChartData(mExercise.getName(), mStatisticsData.getValue())
                .subscribe(mStatisticsData::setValue);
    }

    @Override
    public void onChartBack() {
        mRepo.getPreviousChartData(mExercise.getName(), mStatisticsData.getValue())
                .subscribe(mStatisticsData::setValue);
    }
}
