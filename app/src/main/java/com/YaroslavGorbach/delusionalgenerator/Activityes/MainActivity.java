package com.YaroslavGorbach.delusionalgenerator.Activityes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.YaroslavGorbach.delusionalgenerator.BuildConfig;
import com.YaroslavGorbach.delusionalgenerator.Fragments.AudioListFragment;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogAboutApp;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogChooseTheme;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogFirstOpenMainActivity;
import com.YaroslavGorbach.delusionalgenerator.Fragments.ExercisesFragment_v_2;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements DialogChooseTheme.ChooseThemesListener {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String FIRST_OPEN = "FIRST_OPEN";
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);


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

        mToolbar = findViewById(R.id.toolbar_main);

       ExercisesFragment_v_2 fragmentExercises = ExercisesFragment_v_2.newInstance();
        AudioListFragment fragmentAudioList = new AudioListFragment();

        getSupportFragmentManager().beginTransaction()
               .replace(R.id.main_activity_container,fragmentExercises).commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){

                case (R.id.page_ex):
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_activity_container, fragmentExercises).commit();
                    return true;

                case (R.id.page_star):
                    return true;


                case (R.id.page_voice):
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_activity_container, fragmentAudioList).commit();
                    return true;
            }
            return false;
        });

        /*Оброботка нажатий на елементы меню*/
        mToolbar.setOnMenuItemClickListener(v->{

           switch (v.getItemId()){



               case R.id.theme:

                   DialogChooseTheme dialog = new DialogChooseTheme();
                   dialog.show(getSupportFragmentManager(),"Выбор темы");


                   break;
               case R.id.remember:

                   startActivity(new Intent(this, RemembersActivity.class));

                   break;
               case R.id.rate:

                   Uri uriUrl = Uri.parse("https://play.google.com/store/apps/details?id=com.YaroslavGorbach.delusionalgenerator");
                   Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                   startActivity(launchBrowser);

                   break;
               case R.id.share:

                   Intent sendIntent = new Intent();
                   sendIntent.setAction(Intent.ACTION_SEND);
                   sendIntent.putExtra(Intent.EXTRA_TEXT,
                           "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                   sendIntent.setType("text/plain");
                   startActivity(sendIntent);

                   break;
               case R.id.aboutApp:

                   DialogAboutApp dialog2 = new DialogAboutApp();
                   dialog2.show(getSupportFragmentManager(),"AboutApp");

                   break;
           }

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
}

