package com.YaroslavGorbach.delusionalgenerator;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class AdMob {
    public static void showNativeAdd(Activity activity) {
        AdLoader adLoader = new AdLoader.Builder(activity, "ca-app-pub-6043694180023070~2901457019")
                .forNativeAd(ad -> {
                    if (activity.isDestroyed()) ad.destroy();
                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public static void showBanner(View view) {
        AdView mAdView = (AdView) view;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
