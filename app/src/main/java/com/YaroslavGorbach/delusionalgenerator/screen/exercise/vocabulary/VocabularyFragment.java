package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentVocabularyBinding;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;

public class VocabularyFragment extends Fragment {

    public VocabularyFragment(){ super(R.layout.fragment_vocabulary); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentVocabularyBinding binding = FragmentVocabularyBinding.bind(view);

        // init vm
        int exId = VocabularyFragmentArgs.fromBundle(requireArguments()).getIdEx();
        Repo repo = new Repo.RepoProvider().provideRepo();
        VocabularyVm vm = new ViewModelProvider(this,
                new VocabularyVm.VocabularyVmFactory(repo, exId)).get(VocabularyVm.class);

        // init timer value
        vm.vocabularyEx.getTimerValue().observe(getViewLifecycleOwner(), value ->
                binding.timer.setText(String.valueOf(value)));

        // init onClick
        binding.clickArea.setOnClickListener(v -> vm.vocabularyEx.onClick());

        // init words count
        vm.vocabularyEx.getClickCount().observe(getViewLifecycleOwner(), count ->
                binding.wordsCount.setText(String.valueOf(count)));

        // init short desc
        binding.shortDesc.setText(getString(vm.vocabularyEx.getShortDescId()));
    }
}
