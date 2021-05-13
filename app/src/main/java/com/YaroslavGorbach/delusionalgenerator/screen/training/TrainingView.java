package com.YaroslavGorbach.delusionalgenerator.screen.training;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;
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
        mBinding.training.days.setText(mBinding.getRoot().getContext().getString(R.string.days_without_interruption, training.days));
        mBinding.training.progressIndicator.setProgress(training.getProgress());
        if (training.getIsOver()){
            mBinding.exercises.setVisibility(View.GONE);
            mBinding.trainingIsOver.setVisibility(View.VISIBLE);
        }
    }
}

