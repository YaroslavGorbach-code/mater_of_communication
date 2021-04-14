package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setUpNavControllers();
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
}


