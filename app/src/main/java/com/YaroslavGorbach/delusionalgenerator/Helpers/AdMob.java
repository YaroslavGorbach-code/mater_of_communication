package com.YaroslavGorbach.delusionalgenerator.Helpers;

import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdMob {
    static public void showBanner(View view){
        AdView mAdView = (AdView) view;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
