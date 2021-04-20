package com.YaroslavGorbach.delusionalgenerator.component.description;

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
    public ExModel.Name getExName() {
        return mRepo.getExercise(mExName).name;
    }

    @Override
    public ExModel.Category getCategory() {
        return mRepo.getExercise(mExName).category;
    }

    @Override
    public List<InputData> getStatisticsLast() {
        List<InputData> data = new ArrayList<>();
        for (Statistics s: mRepo.getStatisticsLast(mExName)){
            data.add(new InputData(s.value, s.dataTime));
        }
        Collections.reverse(data);
        return data;
    }

    @Override
    public List<InputData> getStatisticsNext() {
        List<InputData> data = new ArrayList<>();
        for (Statistics s: mRepo.getStatisticsNext(mExName)){
            data.add(new InputData(s.value, s.dataTime));
        }
        Collections.reverse(data);
        return data;
    }

    @Override
    public List<InputData> getStatisticsPrevious() {
        List<InputData> data = new ArrayList<>();
        for (Statistics s: mRepo.getStatisticsPrevious(mExName)){
            data.add(new InputData(s.value, s.dataTime));
        }
        Collections.reverse(data);
        return data;
    }
}
