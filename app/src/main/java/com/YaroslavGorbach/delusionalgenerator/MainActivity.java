package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.screen.description.DescriptionFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking.SpeakingFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.VocabularyFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.training.TrainingFragment;
import com.YaroslavGorbach.delusionalgenerator.workflow.ExerciseWorkflow;
import com.YaroslavGorbach.delusionalgenerator.workflow.NavWorkflow;

public class MainActivity extends AppCompatActivity implements NavWorkflow.Router, TrainingFragment.Router{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (savedInstanceState == null) {
            Fragment fragment = new NavWorkflow();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, fragment)
                    .setPrimaryNavigationFragment(fragment)
                    .commit();
        }
    }
    @Override
    public void openTraining() {
        Fragment fragment = new TrainingFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment)
                .setPrimaryNavigationFragment(fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void openExercise(Exercise.Name name, Exercise.Type type) {
        Fragment fragment = new ExerciseWorkflow();
        fragment.setArguments(ExerciseWorkflow.argsOf(name, type));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment)
                .setPrimaryNavigationFragment(fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

}


