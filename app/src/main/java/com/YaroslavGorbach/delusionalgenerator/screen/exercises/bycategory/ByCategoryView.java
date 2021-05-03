package com.YaroslavGorbach.delusionalgenerator.screen.exercises.bycategory;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentByCategoryBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExsListAdapter;

import java.util.List;

public class ByCategoryView {

    public interface Callback{
        void onExercise(Exercise exercise);
        void onUp();
    }

    private final ExsListAdapter mAdapter;
    private final FragmentByCategoryBinding mBinding;

    public ByCategoryView(FragmentByCategoryBinding binding, Callback callback){
        mBinding = binding;
        mAdapter = new ExsListAdapter(callback::onExercise);
        binding.list.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.list.setAdapter(mAdapter);
        binding.toolbar.setNavigationOnClickListener(v -> callback.onUp());

    }

    public void setTitle(String s){
        mBinding.toolbar.setTitle(s);
    }

    public void setExercises(List<Exercise> exercises) {
        mAdapter.submitList(exercises);
    }

}
