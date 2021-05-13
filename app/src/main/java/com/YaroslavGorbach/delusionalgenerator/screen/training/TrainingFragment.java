package com.YaroslavGorbach.delusionalgenerator.screen.training;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentTrainingBinding;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class TrainingFragment extends Fragment {
    public interface Router{
        void openExercise(Exercise.Name name, Exercise.Type daily);
    }
    public TrainingFragment(){
        super(R.layout.fragment_training);
    }

    private final CompositeDisposable mBag = new CompositeDisposable();
    @Inject Observable<Training> training;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init vm
        TrainingVm vm = new ViewModelProvider(this).get(TrainingVm.class);
        vm.trainingComponent.inject(this);

        // init view
        TrainingView v = new TrainingView(FragmentTrainingBinding.bind(view), new TrainingView.Callback() {
            @Override
            public void onTraining(Exercise exercise) {
                if (exercise.done != exercise.aim)
                    ((Router)requireParentFragment()).openExercise(exercise.getName(), Exercise.Type.DAILY);
            }

            @Override
            public void onUp() { requireActivity().onBackPressed(); }
        });

        mBag.add(training.observeOn(AndroidSchedulers.mainThread()).subscribe(v::setTraining));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBag.dispose();
    }
}
