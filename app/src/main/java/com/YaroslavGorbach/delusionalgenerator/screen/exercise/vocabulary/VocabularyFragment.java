package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentVocabularyBinding;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.TimerImp;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

public class VocabularyFragment extends Fragment{

    public VocabularyFragment(){ super(R.layout.fragment_vocabulary); }

    public static Bundle argsOf(ExModel.Name name){
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        return bundle;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentVocabularyBinding binding = FragmentVocabularyBinding.bind(view);

        // init vm
        ExModel.Name name = (ExModel.Name) requireArguments().getSerializable("name");
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());
        VocabularyVm vm = new ViewModelProvider(this,
                new VocabularyVm.VocabularyVmFactory(repo, name, new TimerImp(), new StatisticsManagerImp())).get(VocabularyVm.class);

        // init timer value
        vm.vocabularyEx.getTimerValue().observe(getViewLifecycleOwner(), value ->
                binding.timer.setText(String.valueOf(value)));

        // init onClick
        binding.clickArea.setOnClickListener(v -> vm.vocabularyEx.onClick());

        // init toolbar
        binding.toolbar.setNavigationOnClickListener(v-> ((Navigation)requireActivity()).up());

        // init words count
        vm.vocabularyEx.getClickCount().observe(getViewLifecycleOwner(), count ->
                binding.wordsCount.setText(String.valueOf(count)));

        // init short desc
        binding.shortDesc.setText(getString(vm.vocabularyEx.getShortDescId()));

        // init finish event
        vm.vocabularyEx.onTimerFinish().observe(getViewLifecycleOwner(), isFinis -> {
            if (isFinis){
                binding.clickArea.setClickable(false);
                binding.clickArea.setFocusable(false);
                ((Navigation)requireActivity()).showFinishDialog(vm.vocabularyEx.getResultState());
            }
        });
    }
}
