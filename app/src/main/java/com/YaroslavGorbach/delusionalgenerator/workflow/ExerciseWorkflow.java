package com.YaroslavGorbach.delusionalgenerator.workflow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.screen.description.DescriptionFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking.SpeakingFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.VocabularyFragment;

public class ExerciseWorkflow extends Fragment implements DescriptionFragment.Router {
   public ExerciseWorkflow(){
       super(R.layout.workflow_exercise);
   }

    public static Bundle argsOf(Exercise.Name name, Exercise.Type type) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        bundle.putSerializable("type", type);
        return bundle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.exercise_container, DescriptionFragment.class, DescriptionFragment.argsOf(
                            (Exercise.Name)requireArguments().getSerializable("name"),
                            (Exercise.Type)requireArguments().getSerializable("type")))
                    .commit();
        }
    }

    @Override
    public void openSpeaking(Exercise.Name name, Exercise.Type type) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.exercise_container, SpeakingFragment.class, SpeakingFragment.argsOf(name, type))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void openVocabulary(Exercise.Name name, Exercise.Type type) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.exercise_container, VocabularyFragment.class, VocabularyFragment.argsOf(name, type))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
