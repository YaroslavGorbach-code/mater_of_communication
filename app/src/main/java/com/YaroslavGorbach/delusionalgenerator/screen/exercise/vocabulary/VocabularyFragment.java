package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Toast;

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
        // init vm
        ExModel.Name name = (ExModel.Name) requireArguments().getSerializable("name");
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());
        VocabularyVm vm = new ViewModelProvider(this,
                new VocabularyVm.VocabularyVmFactory(repo, name, new TimerImp(), new StatisticsManagerImp())).get(VocabularyVm.class);

        // init view
        VocabularyView v = new VocabularyView(FragmentVocabularyBinding.bind(view), new VocabularyView.Callback() {
            @Override
            public void onUp() { ((Navigation)requireActivity()).up(); }

            @Override
            public void onClick() { vm.vocabularyEx.onClick(); }

        });
        v.setTitle(getString(name.getNameId()));
        v.setShortDesc(getString(vm.vocabularyEx.getShortDescId()));
        vm.vocabularyEx.getTimerValue().observe(getViewLifecycleOwner(), v::setTimerValue);
        vm.vocabularyEx.getClickCount().observe(getViewLifecycleOwner(), count ->
                v.setClickCount(String.valueOf(count)));
        vm.vocabularyEx.onTimerFinish().observe(getViewLifecycleOwner(), isFinis -> {
            if (isFinis){
                v.onTimerFinish();
                ((Navigation)requireActivity()).showFinishDialog(vm.vocabularyEx.getResultState());
            }
        });
    }
}
