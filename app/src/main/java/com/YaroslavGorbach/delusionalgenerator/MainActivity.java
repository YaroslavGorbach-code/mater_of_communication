package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.screen.description.DescriptionFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking.SpeakingFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.FinishDialog;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary.VocabularyFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.NavFragment;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Navigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, new NavFragment()).commit();
        }
    }

    @Override
    public void openSpeakingEx(ExModel.Name name) {
        getSupportFragmentManager().beginTransaction()
                .hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.main_container)))
                .add(R.id.main_container, SpeakingFragment.class, SpeakingFragment.argsOf(name))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null).commit();
    }

    @Override
    public void openVocabularyEx(ExModel.Name name) {
        getSupportFragmentManager().beginTransaction()
                .hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.main_container)))
                .add(R.id.main_container, VocabularyFragment.class, VocabularyFragment.argsOf(name))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null).commit();
    }

    @Override
    public void openDescription(ExModel.Name name) {
        getSupportFragmentManager().beginTransaction()
                .hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.main_container)))
                .add(R.id.main_container, DescriptionFragment.class, DescriptionFragment.argsOf(name))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null).commit();
    }

    @Override
    public void showFinishDialog(VocabularyEx.Result result) {
       FinishDialog alertDialog = new FinishDialog();
       alertDialog.setArguments(FinishDialog.argsOf(result));
       alertDialog.show(getSupportFragmentManager(), "null");
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void up() {
        onBackPressed();
    }
}


