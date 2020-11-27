package com.YaroslavGorbach.delusionalgenerator.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.YaroslavGorbach.delusionalgenerator.Fragments.AllExsByCategoryFragmentDirections;
import com.YaroslavGorbach.delusionalgenerator.Fragments.AudioListFragment;
import com.YaroslavGorbach.delusionalgenerator.Fragments.AudioListFragmentDirections;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogChooseTheme;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogDeleteRecords;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogFirstOpenMainActivity;
import com.YaroslavGorbach.delusionalgenerator.Fragments.ExercisesFragmentDirections;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements DialogChooseTheme.ChooseThemesListener,
        DialogDeleteRecords.DeleteRecordsListener {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String FIRST_OPEN = "FIRST_OPEN";
    private  NavHostFragment mNavHostFragment;

    private MaterialToolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar_main_a);

        /*Установка оброботки нажатий на элементы нижней навигации*/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttm_nav);
         mNavHostFragment =(NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,
                mNavHostFragment.getNavController());

        /*Показ диалога с описанием приложения если оно открываеться впервые*/
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        boolean firstOpen = sharedPreferences.getBoolean(FIRST_OPEN, true);
        if(firstOpen){
            DialogFirstOpenMainActivity dialog = new DialogFirstOpenMainActivity();
            dialog.show(getSupportFragmentManager(),"first open");

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(FIRST_OPEN, false);
            editor.apply();
        }
        mToolbar.setOnMenuItemClickListener(item -> {
            new DialogDeleteRecords().show(getSupportFragmentManager(),"delete");
            return true;
        });
    }


    /*Установка темы*/
    private void setTheme(){
        String color = Repo.getInstance(MainActivity.this).getThemeState();
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

    @Override
    public void onClickDelete() {
        /*Получаем файлы из деректории*/
        String mPath = this.getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(mPath);
        File[] allFiles = directory.listFiles();

        if (allFiles != null) {
            for (File f : allFiles) {
                f.delete();
            }
            NavDirections action = AudioListFragmentDirections.actionAudioListFragmentSelf();
            Navigation.findNavController(this,R.id.fragment).navigate(action);

        }
    }
}

