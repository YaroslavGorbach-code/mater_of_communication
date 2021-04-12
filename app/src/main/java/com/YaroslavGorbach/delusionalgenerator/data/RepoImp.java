package com.YaroslavGorbach.delusionalgenerator.data;

import com.YaroslavGorbach.delusionalgenerator.R;

import java.util.ArrayList;
import java.util.List;

public class RepoImp implements Repo {
    private final List<ExModel> mExercises = new ArrayList<>();

     public RepoImp(){
         mExercises.add(new ExModel(
                 0,
                 ExModel.Name.LINGUISTIC_PYRAMIDS,
                 ExModel.Category.SPEAKING,
                 new ExModel.Description(
                         "coming soon...",
                         "coming soon...",
                         "coming soon...",
                         "coming soon..."),
                 R.drawable.ex1_backgraund_v_2));
    }

    @Override
    public List<ExModel> getExercises() {
        return mExercises;
    }
}
