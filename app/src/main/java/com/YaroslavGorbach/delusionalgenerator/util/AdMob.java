package com.YaroslavGorbach.delusionalgenerator.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAdOptions;

public class AdMob {
    private InterstitialAd mInterstitialAd;
    private static final String ADD_PREFERENCES = "ADD_PREFERENCES";
    private static final String CLICK_COUNT = "CLICK_COUNT";
    private SharedPreferences mSettings;
    private int mCount;

    public AdMob(Context context){
        mSettings = context.getSharedPreferences(ADD_PREFERENCES, Context.MODE_PRIVATE);

        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();


        InterstitialAd.load(context,"ca-app-pub-6043694180023070/5649596511", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i("TAG", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("TAG", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });

        if (mInterstitialAd!=null){
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    Log.d("TAG", "The ad failed to show.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                    mSettings.edit().putInt(CLICK_COUNT, 0).apply();
                }
            });
        }
    }

    static public void showBanner(View view) {
        AdView mAdView = (AdView) view;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public static void showNativeAdd(Activity activity, TemplateView  templateView){
        MobileAds.initialize(activity, "ca-app-pub-6043694180023070~2901457019");
        AdLoader adLoader = new AdLoader.Builder(activity, "ca-app-pub-6043694180023070/4522089359")
                .forUnifiedNativeAd(unifiedNativeAd -> {
                    if (activity.isDestroyed()) {
                        unifiedNativeAd.destroy();
                    }
                    //templateView.setStyles(styles);
                    templateView.setNativeAd(unifiedNativeAd);
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        adLoader.loadAds(new AdRequest.Builder().build(), 3);

    }

     public void showInterstitialAd(Activity activity){

         mCount = mSettings.getInt(CLICK_COUNT, 0);
         mSettings.edit().putInt(CLICK_COUNT, mCount+1).apply();
         if (mInterstitialAd != null && mCount>4) {
            mInterstitialAd.show(activity);
         } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

}
