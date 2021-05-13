package com.YaroslavGorbach.delusionalgenerator.screen.exercises.bycategory;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentByCategoryBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExsListAdapter;

import java.util.List;

public class ByCategoryView {

    public interface Callback{
        void onExercise(Exercise exercise);
    }

    private final ExsListAdapter mAdapter;

    public ByCategoryView(FragmentByCategoryBinding binding, Callback callback){
        mAdapter = new ExsListAdapter(callback::onExercise);
        binding.list.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.list.setAdapter(mAdapter);

    }

    public void setExercises(List<Exercise> exercises) {
        mAdapter.submitList(exercises);
    }

}
