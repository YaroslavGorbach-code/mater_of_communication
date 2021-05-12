package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.component.vocabulary.Vocabulary;
import com.YaroslavGorbach.delusionalgenerator.component.vocabulary.VocabularyImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentVocabularyBinding;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.di.VocabularyComponent;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.timer.TimerImp;

import javax.inject.Inject;

public class VocabularyFragment extends Fragment{

    public VocabularyFragment(){ super(R.layout.fragment_vocabulary); }

    public static Bundle argsOf(Exercise.Name name, Exercise.Type type){
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        bundle.putSerializable("type", type);
        return bundle;
    }

    @Inject Vocabulary vocabulary;
    @Inject AdManager adManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init vm
        Exercise.Name name = (Exercise.Name) requireArguments().getSerializable("name");
        Exercise.Type type = (Exercise.Type) requireArguments().getSerializable("type");
        VocabularyVm vm = new ViewModelProvider(this).get(VocabularyVm.class);
        vm.getVocabularyComponent(name, type).inject(this);

        // init view
        VocabularyView v = new VocabularyView(FragmentVocabularyBinding.bind(view), new VocabularyView.Callback() {
            @Override
            public void onUp() { requireActivity().onBackPressed(); }

            @Override
            public void onClick() { vocabulary.onClick(); }

        });

        v.setTitle(getString(name.getNameId()));
        v.setShortDesc(getString(vocabulary.getShortDescId()));
        vocabulary.getTimerValue().observe(getViewLifecycleOwner(), v::setTimerValue);
        vocabulary.getClickCount().observe(getViewLifecycleOwner(), count ->
                v.setClickCount(String.valueOf(count)));
        vocabulary.onTimerFinish().observe(getViewLifecycleOwner(), isFinis -> {
            if (isFinis){
                v.onTimerFinish();
                FinishDialog alertDialog = new FinishDialog();
                alertDialog.setArguments(FinishDialog.argsOf(vocabulary.getResultState()));
                alertDialog.show(getChildFragmentManager(), "null");
            }
        });
        adManager.loadInterstitialAd(view.getContext());

    }

    @Override
    public void onDestroy() {
        vocabulary.saveStatistics();
        adManager.showInterstitialAd(requireActivity());
        super.onDestroy();
    }
}
