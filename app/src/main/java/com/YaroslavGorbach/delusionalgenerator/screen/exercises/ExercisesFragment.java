package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.exercises.ExercisesImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentExercisesBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ExercisesFragment extends Fragment {
    public interface Router{
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openExsByCategory(Exercise.Category category);
        void openTraining();
    }

    private final CompositeDisposable mBag = new CompositeDisposable();
    public ExercisesFragment() {
        super(R.layout.fragment_exercises);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init vm
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());
        ExercisesVm vm = new ViewModelProvider(this, new ExercisesVm.ExercisesVmFactory(
                new ExercisesImp(repo), new AdManagerImp(repo))).get(ExercisesVm.class);

        // init view
        ExercisesView v = new ExercisesView(FragmentExercisesBinding.bind(view), new ExercisesView.Callback() {
            @Override
            public void onExercise(Exercise exercise) {
                ((Router) requireParentFragment()).openExercise(exercise.getName(), Exercise.Type.COMMON);
            }

            @Override
            public void onCategory(Exercise.Category category) {
                ((Router) requireParentFragment()).openExsByCategory(category);
            }

            @Override
            public void onTraining() {
                ((Router) requireParentFragment()).openTraining();
            }

        });

        v.setExercises(vm.exercises.getExercises());
        v.setSpeakingCount(vm.exercises.getExercises(Exercise.Category.SPEAKING).size());
        v.setVocabularyCount(vm.exercises.getExercises(Exercise.Category.VOCABULARY).size());
        v.setTongueTwistersCount(vm.exercises.getExercises(Exercise.Category.TONGUE_TWISTER).size());
        v.refreshAd(requireActivity(), vm.adManager);
        mBag.add(vm.exercises.getTraining().observeOn(AndroidSchedulers.mainThread()).subscribe(v::setTraining));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBag.dispose();
    }

}

