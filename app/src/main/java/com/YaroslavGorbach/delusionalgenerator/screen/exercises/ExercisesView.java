package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Training;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentExercisesBinding;

import java.util.List;

public class ExercisesView {
    public interface Callback{
        void onExercise(Exercise exercise);
        void onTraining();
    }
    private final ExsListAdapter mAdapter;
    private final FragmentExercisesBinding mBinding;

    public ExercisesView(FragmentExercisesBinding binding, Callback callback){
        mBinding = binding;
        mAdapter = new ExsListAdapter(callback::onExercise);
        mAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
        binding.exsList.setHasFixedSize(true);
        binding.exsList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL,
                false));
        binding.exsList.setAdapter(mAdapter);
        binding.training.item.setOnClickListener(v -> callback.onTraining());
    }

    void setExercises(List<Exercise> exercises){
        mAdapter.submitList(exercises);
    }

    public void setTraining(Training training) {
        mBinding.training.days.setText("Дней подряд: " + training.days); // TODO: 4/28/2021 fix it later
        mBinding.training.progressIndicator.setProgress(training.getProgress());
    }
}
