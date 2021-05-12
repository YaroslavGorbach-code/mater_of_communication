package com.YaroslavGorbach.delusionalgenerator;

import android.app.Application;

import com.YaroslavGorbach.delusionalgenerator.di.AppComponent;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerAppComponent;


public class App extends Application {
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(this);

        // if first open check system theme and set it as app
//        if (repo.getFirstOpen()){
//            int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//            repo.setNightMod(nightModeFlags == Configuration.UI_MODE_NIGHT_YES);
//        }
//
//        if (repo.getNightMod()){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
    }
}
