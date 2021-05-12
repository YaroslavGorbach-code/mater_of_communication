package com.YaroslavGorbach.delusionalgenerator;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.RepoProvider;
import com.YaroslavGorbach.delusionalgenerator.di.AppComponent;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerAppComponent;
import com.YaroslavGorbach.delusionalgenerator.di.DaggerRepoComponent;
import com.google.android.gms.ads.MobileAds;


public class App extends Application implements RepoProvider {
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(DaggerRepoComponent.factory().create(this));
        createChannel();
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, initializationStatus -> {});

        // if first open check system theme and set it as app
        if (provideRepo().getFirstOpen()){
            int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            provideRepo().setNightMod(nightModeFlags == Configuration.UI_MODE_NIGHT_YES);
        }
        if (provideRepo().getNightMod()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public Repo provideRepo() {
        return appComponent.provideRepo();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "1",
                    "App notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("App notification");
            NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
