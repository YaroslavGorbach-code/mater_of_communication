package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExercisesFragment;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;

public class VocabularyFragment extends Fragment {

    public VocabularyFragment(){ super(R.layout.fragment_vocabulary); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // show add
        AdMob.showBanner(view.findViewById(R.id.banner));

        // init vm
        int exId = VocabularyFragmentArgs.fromBundle(requireArguments()).getIdEx();
        Repo repo = new Repo.RepoProvider().provideRepo();
        VocabularyVm vm = new ViewModelProvider(this,
                new VocabularyVm.VocabularyVmFactory(repo, exId)).get(VocabularyVm.class);

        // init timer value
        TextView timer = view.findViewById(R.id.timer);
        vm.vocabularyEx.getTimerValue().observe(getViewLifecycleOwner(), value -> timer.setText(String.valueOf(value)));

        // init onClick
        ConstraintLayout clickArea = view.findViewById(R.id.clickAria);
        clickArea.setOnClickListener(v -> vm.vocabularyEx.onClick());

        // init words count
        TextView wordsCount = view.findViewById(R.id.wordsCount);
        vm.vocabularyEx.getClickCount().observe(getViewLifecycleOwner(), count -> wordsCount.setText(String.valueOf(count)));

        // init short desc
        TextView shortDEsc = view.findViewById(R.id.short_desc);
        shortDEsc.setText(vm.vocabularyEx.getShortDesc());
    }
}
