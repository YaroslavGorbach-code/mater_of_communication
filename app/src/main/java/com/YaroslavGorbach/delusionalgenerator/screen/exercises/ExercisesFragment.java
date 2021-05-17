package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.exercises.Exercises;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentExercisesBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.util.TimeAndDataUtil;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;

public class ExercisesFragment extends Fragment {

    public interface Router {
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openExsByCategory(Exercise.Category category);
        void openTraining();
    }

    private final CompositeDisposable mBag = new CompositeDisposable();
    @Inject Exercises exercises;
    @Inject AdManager adManager;
    private Training mCurrentTraining;

    public ExercisesFragment() {
        super(R.layout.fragment_exercises);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExercisesVm vm = new ViewModelProvider(this).get(ExercisesVm.class);
        vm.exercisesComponent.inject(this);

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

            @Override
            public void onRandom() {
                ((Router) requireParentFragment()).openExercise(exercises.getRandomEx().getName(), Exercise.Type.COMMON);
            }

        });

        v.setExercises(exercises.getExercises());
        v.setSpeakingCount(exercises.getExercises(Exercise.Category.SPEAKING).size());
        v.setVocabularyCount(exercises.getExercises(Exercise.Category.VOCABULARY).size());
        v.setTongueTwistersCount(exercises.getExercises(Exercise.Category.TONGUE_TWISTER).size());
        v.refreshAd(requireActivity(), adManager);
        mBag.add(exercises.getTraining().observeOn(AndroidSchedulers.mainThread()).subscribe(training -> {
            mCurrentTraining = training;
            v.setTraining(training);
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCurrentTraining!= null && TimeAndDataUtil.isNewTrainingAllow(mCurrentTraining.date, new Date())){
            requireActivity().recreate();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBag.dispose();

    }

}

