package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.YaroslavGorbach.delusionalgenerator.screen.settings.DialogChooseTheme;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.data.oldDataLayer.Repo_SQLite;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements DialogChooseTheme.ChooseThemesListener{

    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String FIRST_OPEN = "FIRST_OPEN";
    private AdMob mAdMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mAdMod = new AdMob(this);

        /*Установка оброботки нажатий на элементы нижней навигации*/
        setUpNavControllers();

        /*Показ диалога с описанием приложения если оно открываеться впервые*/
        showFirsOpenDialog();

    }

    @SuppressLint("NonConstantResourceId")
    private void setUpNavControllers() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttm_nav);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_main_a);

        NavController navController = Navigation.findNavController(this, R.id.fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()){
                case R.id.exercisesDescriptionFragment:
                    toolbar.getMenu().clear();
                    toolbar.inflateMenu(R.menu.menu_description);
                    bottomNavigationView.setVisibility(View.GONE);
                    mAdMod.showInterstitialAd(this);
                    break;
                case R.id.exercisesFragment:
                    toolbar.getMenu().clear();
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    break;
                case R.id.statisticsFragment:
                    toolbar.getMenu().clear();
                    toolbar.inflateMenu(R.menu.menu_clear_statistics);
                    break;
                case R.id.settingsFragment2:
                    toolbar.setNavigationIcon(null);
                    toolbar.getMenu().clear();
                    break;
                case R.id.audioListFragment:
                    toolbar.setNavigationIcon(null);
                    toolbar.getMenu().clear();
                    toolbar.inflateMenu(R.menu.menu_records);
                    break;
                case R.id.allExsByCategoryFragment:
                case R.id.randomTrainingsFragment:
                    toolbar.getMenu().clear();
                    bottomNavigationView.setVisibility(View.GONE);
                    break;
                case R.id.speaking_fragment:
                case R.id.vocabulary_fragment:
                case R.id.tongue_twister_fragment:
                    toolbar.getMenu().clear();
                    break;
            }
        });

    }

    private void showFirsOpenDialog() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        boolean firstOpen = sharedPreferences.getBoolean(FIRST_OPEN, true);
        if(firstOpen){
            DialogFirstOpenMainActivity dialog = new DialogFirstOpenMainActivity();
            dialog.show(getSupportFragmentManager(),"first open");

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(FIRST_OPEN, false);
            editor.apply();
        }
    }


    /*Установка темы*/
    private void setTheme(){
        String color = Repo_SQLite.getInstance(MainActivity.this).getThemeState();
        switch (color){
            case "blue":
                setTheme(R.style.AppTheme_blue);
                break;
            case "green":
                setTheme(R.style.AppTheme_green);
                break;
            case "orange":
                setTheme(R.style.AppTheme_orange);
                break;
            case "red":
                setTheme(R.style.AppTheme_red);
                break;
            case "purple":
                setTheme(R.style.AppTheme_purple);
                break;
        }
    }

    /*Пересоздание активити при выборе новой темы*/
    @Override
    public void onClickTheme(DialogFragment dialog) {
        recreate();
    }

    }


