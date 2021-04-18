package com.YaroslavGorbach.delusionalgenerator.component.Description;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.Statistics;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DescriptionImp implements Description {
    private final Repo mRepo;
    private final int mExId;

    public DescriptionImp(Repo repo, int exId){
        mRepo = repo;
        mExId = exId;
    }
    @Override
    public int getDescriptionId() {
        return mRepo.getExercises().get(mExId).descriptionId;
    }

    @Override
    public int getImageId() {
        return mRepo.getExercises().get(mExId).imageId;
    }

    @Override
    public ExModel.Name getExName() {
        return mRepo.getExercises().get(mExId).name;
    }

    @Override
    public ExModel.Category getCategory() {
        return mRepo.getExercises().get(mExId).category;
    }

    @Override
    public List<InputData> getStatistics() {
        List<InputData> data = new ArrayList<>();
        for (Statistics s: mRepo.getStatistics(mExId)){
            data.add(new InputData(s.value, s.dataTime));
        }
        Collections.reverse(data);
        return data;
    }
}
