package com.YaroslavGorbach.delusionalgenerator.workflow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExercisesFragment;

public class ExercisesWorkflow extends Fragment implements ExercisesFragment.Router {

    public interface Router{
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openTraining();
    }

    public ExercisesWorkflow(){
        super(R.layout.workflow_exercises);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.exercises_container, new ExercisesFragment())
                    .commit();
        }
    }

    @Override
    public void openExercise(Exercise.Name name, Exercise.Type type) {
       ((Router)requireParentFragment()).openExercise(name, type);
    }

    @Override
    public void openTraining() {
        ((Router)requireParentFragment()).openTraining();
    }

}
