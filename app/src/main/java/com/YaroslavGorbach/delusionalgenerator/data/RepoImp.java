package com.YaroslavGorbach.delusionalgenerator.data;

import com.YaroslavGorbach.delusionalgenerator.R;

import java.util.ArrayList;
import java.util.List;

public class RepoImp implements Repo {
    private List<ExModel> mExercises = new ArrayList<>();

     public RepoImp(){
         mExercises.add(new ExModel(
                 0,
                 "Лингвистические пирамиды",
                 ExModel.ExCategory.SPEAKING,
                 new ExModel.Description(
                         "coming soon...",
                         "coming soon...",
                         "coming soon...",
                         "coming soon..."),
                 R.drawable.ex1_backgraund_v_2,
                 0,
                 1));

    }

    @Override
    public List<ExModel> getExercises() {
        return mExercises;
    }
}
