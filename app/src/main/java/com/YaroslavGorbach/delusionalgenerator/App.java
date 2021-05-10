package com.YaroslavGorbach.delusionalgenerator;

import android.app.Application;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repo repo = new Repo.RepoProvider().provideRepo(this);
        // if first open check system theme and set it as app
        if (repo.getFirstOpen()){
            int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            repo.setNightMod(nightModeFlags == Configuration.UI_MODE_NIGHT_YES);
        }

        if (repo.getNightMod()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
