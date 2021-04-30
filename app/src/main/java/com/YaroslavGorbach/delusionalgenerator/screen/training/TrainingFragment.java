package com.YaroslavGorbach.delusionalgenerator.screen.training;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDailyTrainingBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class TrainingFragment extends Fragment {
    public TrainingFragment(){
        super(R.layout.fragment_daily_training);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDailyTrainingBinding binding = FragmentDailyTrainingBinding.bind(view);

        // init vm
        TrainingVm vm = new ViewModelProvider(this,
                new TrainingVm.DailyTrainingVmFactory(new Repo.RepoProvider().provideRepo(requireContext()))).get(TrainingVm.class);

        // init app bar
        binding.toolbar.setNavigationOnClickListener(v ->
                ((Navigation)requireActivity()).up());

        // init list
        TrainingListAdapter adapter = new TrainingListAdapter(dailyTrainingEx -> {
            ((Navigation)requireActivity()).openDescription(dailyTrainingEx.getName(), Exercise.Type.DAILY);
        });

        vm.training
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyTrainingM -> {
                    adapter.submitList(dailyTrainingM.exercises);
                });

        binding.exercises.setAdapter(adapter);
        binding.exercises.setLayoutManager(new LinearLayoutManager(requireContext()));

    }
}
