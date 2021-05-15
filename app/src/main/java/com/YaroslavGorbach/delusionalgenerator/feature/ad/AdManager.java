package com.YaroslavGorbach.delusionalgenerator.feature.ad;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

public interface AdManager {
    int INTERSTITIAL_SHOW_LIMIT = 1;
    void showBanner(Context context, ViewGroup adContainer);
    void showNativeAd(Activity activity, ViewGroup adPlaceholder);
    void showInterstitialAd(Activity activity);
    void loadInterstitialAd(Context context);
}
