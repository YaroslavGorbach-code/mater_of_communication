package com.YaroslavGorbach.delusionalgenerator.screen.dailyTraining;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingEx;
import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingM;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDailyTrainingBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;

public class DailyTrainingFragment extends Fragment {
    public DailyTrainingFragment(){
        super(R.layout.fragment_daily_training);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDailyTrainingBinding binding = FragmentDailyTrainingBinding.bind(view);

        // init vm
        DailyTrainingVm vm = new ViewModelProvider(this,
                new DailyTrainingVm.DailyTrainingVmFactory(new Repo.RepoProvider().provideRepo(requireContext()))).get(DailyTrainingVm.class);

        // init app bar
        binding.toolbar.setNavigationOnClickListener(v ->
                ((Navigation)requireActivity()).up());

        // init list
        DailyTrainingExsAdapter adapter = new DailyTrainingExsAdapter(dailyTrainingEx -> {
            ((Navigation)requireActivity()).openDescription(dailyTrainingEx.getExercise().name);
        });

        vm.dailyTraining.getDailyTraining()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyTrainingM -> {
                    adapter.submitList(dailyTrainingM.exercises);
                });

        binding.exercises.setAdapter(adapter);
        binding.exercises.setLayoutManager(new LinearLayoutManager(requireContext()));

    }
}