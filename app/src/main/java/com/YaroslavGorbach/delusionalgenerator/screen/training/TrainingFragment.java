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
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentTrainingBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class TrainingFragment extends Fragment {
    public TrainingFragment(){
        super(R.layout.fragment_training);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTrainingBinding binding = FragmentTrainingBinding.bind(view);

        // init vm
        TrainingVm vm = new ViewModelProvider(this,
                new TrainingVm.DailyTrainingVmFactory(new Repo.RepoProvider().provideRepo(requireContext()))).get(TrainingVm.class);


        // init list
        TrainingListAdapter adapter = new TrainingListAdapter(exercise -> {
                if (exercise.done != exercise.aim)
            ((Navigation)requireActivity()).openDescription(exercise.getName(), Exercise.Type.DAILY);
        });

        // init app bar
        binding.training.close.setVisibility(View.VISIBLE);
        binding.training.close.setOnClickListener(v-> ((Navigation)requireActivity()).up());
        vm.training.observeOn(AndroidSchedulers.mainThread())
                .subscribe(training -> {
                    adapter.submitList(training.exercises);
                    binding.training.days.setText("Дней подряд: " + training.days); // TODO: 4/28/2021 fix it later
                    binding.training.progressIndicator.setProgress(training.getProgress());
                });

        binding.exercises.setAdapter(adapter);
        binding.exercises.setLayoutManager(new LinearLayoutManager(requireContext()));

    }
}
