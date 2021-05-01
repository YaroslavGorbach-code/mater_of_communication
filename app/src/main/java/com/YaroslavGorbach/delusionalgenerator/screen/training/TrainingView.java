package com.YaroslavGorbach.delusionalgenerator.screen.training;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Training;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentTrainingBinding;

public class TrainingView {
    public interface Callback{
        void onTraining(Exercise exercise);
        void onUp();
    }

    private final TrainingListAdapter mAdapter;
    private final FragmentTrainingBinding mBinding;

    public TrainingView(FragmentTrainingBinding binding, Callback callback){
        mBinding = binding;
        mAdapter = new TrainingListAdapter(callback::onTraining);
        binding.exercises.setAdapter(mAdapter);
        binding.exercises.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.training.close.setVisibility(View.VISIBLE);
        binding.training.close.setOnClickListener(v-> callback.onUp());
    }


    public void setTraining(Training training) {
        mAdapter.submitList(training.exercises);
        mBinding.training.days.setText("Дней подряд: " + training.days); // TODO: 4/28/2021 fix it later
        mBinding.training.progressIndicator.setProgress(training.getProgress());
        if (training.getIsOver()){
            mBinding.exercises.setVisibility(View.GONE);
            mBinding.trainingIsOver.setVisibility(View.VISIBLE);
        }
    }
}

