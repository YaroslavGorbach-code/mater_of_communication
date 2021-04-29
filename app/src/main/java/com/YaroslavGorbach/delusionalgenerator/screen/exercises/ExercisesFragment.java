package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingM;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentExercisesBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;

public class ExercisesFragment extends Fragment {

    public ExercisesFragment(){ super(R.layout.fragment_exercises); }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentExercisesBinding binding = FragmentExercisesBinding.bind(view);

        // init list
        ExercisesVm vm = new ViewModelProvider(this, new ExercisesVm.ExercisesVmFactory(new Repo.RepoProvider().provideRepo(requireContext()))).get(ExercisesVm.class);

        ExsAdapter adapter = new ExsAdapter(exModel ->
                ((Navigation) requireActivity()).openDescription(exModel.getName()));

        adapter.submitList(vm.getAllExs());
        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
        binding.exsList.setHasFixedSize(true);
        binding.exsList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false));
        binding.exsList.setAdapter(adapter);

        // init daily training
        binding.dailyTraining.item.setOnClickListener(v -> ((Navigation)requireActivity()).openDailyTraining());
        vm.dailyTraining.getDailyTraining()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyTrainingM -> {
            binding.dailyTraining.itemDays.setText("Дней подряд: " + dailyTrainingM.days); // TODO: 4/28/2021 fix it later
            binding.dailyTraining.progressIndicator.setProgress(dailyTrainingM.progress);
        });
    }
}

