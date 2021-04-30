package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.screen.training.TrainingFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.description.DescriptionFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking.SpeakingFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.FinishDialog;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.VocabularyFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.NavFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

public class MainActivity extends AppCompatActivity implements Navigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, new NavFragment()).commit();
        }
    }

    @Override
    public void openSpeakingEx(Exercise.Name name, Exercise.Type type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, SpeakingFragment.class, SpeakingFragment.argsOf(name, type))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openVocabularyEx(Exercise.Name name, Exercise.Type type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, VocabularyFragment.class, VocabularyFragment.argsOf(name, type))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openDescription(Exercise.Name name, Exercise.Type type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, DescriptionFragment.class, DescriptionFragment.argsOf(name, type))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showFinishDialog(VocabularyEx.Result result) {
       FinishDialog alertDialog = new FinishDialog();
       alertDialog.setArguments(FinishDialog.argsOf(result));
       alertDialog.show(getSupportFragmentManager(), "null");
    }

    @Override
    public void openTraining() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new TrainingFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void up() {
        onBackPressed();
    }
}


