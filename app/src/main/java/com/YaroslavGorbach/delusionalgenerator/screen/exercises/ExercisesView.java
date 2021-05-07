package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.feature.AdManager;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.room.Training;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentExercisesBinding;

import java.util.List;

public class ExercisesView {

    public interface Callback{
        void onExercise(Exercise exercise);
        void onCategory(Exercise.Category category);
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

        binding.categories.vocabulary.setOnClickListener(v -> callback.onCategory(Exercise.Category.VOCABULARY));
        binding.categories.speaking.setOnClickListener(v -> callback.onCategory(Exercise.Category.SPEAKING));
        binding.categories.tongueTwisters.setOnClickListener(v -> callback.onCategory(Exercise.Category.TONGUE_TWISTER));
    }

    void setExercises(List<Exercise> exercises){
        mAdapter.submitList(exercises);
    }

    public void setTraining(Training training) {
        mBinding.training.days.setText(mBinding.getRoot().getContext().getString(R.string.days_without_interruption, training.days));
        mBinding.training.progressIndicator.setProgress(training.getProgress());
    }

    public void setVocabularyCount(int count){
        mBinding.categories.countVocabulary.setText(String.valueOf(count));
    }
    public void setTongueTwistersCount(int count){
        mBinding.categories.countTt.setText(String.valueOf(count));
    }
    public void setSpeakingCount(int count){
        mBinding.categories.countSpeaking.setText(String.valueOf(count));
    }

    public void refreshAd(Activity activity, AdManager adManager) {
        adManager.showNativeAd(activity, mBinding.adPlaceholder);
    }
}
