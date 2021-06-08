package com.YaroslavGorbach.delusionalgenerator.data.local.pref.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.YaroslavGorbach.delusionalgenerator.data.local.pref.common.CommonPrefStorage;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;

import java.util.Locale;

public class CommonPrefStorageImp implements CommonPrefStorage {
    private final SharedPreferences mSharedPreferences;

    public CommonPrefStorageImp(Context context){
        mSharedPreferences = context.getSharedPreferences("com.YaroslavGorbach.delusionalgenerator", Context.MODE_PRIVATE);
    }

    @Override
    public boolean getFirstOpen() {
        return mSharedPreferences.getBoolean("firstOpen", true);
    }

    @Override
    public void setFirstOpen(boolean firstOpen) {
        mSharedPreferences.edit().putBoolean("firstOpen", firstOpen).apply();
    }

    @Override
    public void setInterstitialAdCount(int count) {
        mSharedPreferences.edit().putInt("interstitialAdCount", count).apply();
    }

    @Override
    public int getInterstitialAdCount() {
        return mSharedPreferences.getInt("interstitialAdCount", AdManager.INTERSTITIAL_SHOW_LIMIT);
    }

    @Override
    public void setNightMod(boolean nightMod) {
        mSharedPreferences.edit().putBoolean("nightMod", nightMod).apply();
    }

    @Override
    public boolean getNightMod() {
        return mSharedPreferences.getBoolean("nightMod", false);
    }

    @Override
    public boolean getAdIsAllow() {
        return mSharedPreferences.getBoolean("adIsAllow", true);
    }

    @Override
    public void setAdIsAllow(boolean isAllow) {
        mSharedPreferences.edit().putBoolean("adIsAllow", isAllow).apply();
    }

    @Override
    public void setTimeLastReviewAsc(long time) {
        mSharedPreferences.edit().putLong("timeLastReviewAsc", time).apply();
    }

    @Override
    public long getTimeLastReviewAsc() {
        return mSharedPreferences.getLong("timeLastReviewAsc", 0);
    }

    @Override
    public boolean getIsEnLanguage() {
        return mSharedPreferences.getBoolean("IsLocaleEn", Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage()));
    }

    @Override
    public void setLocaleIsEn(boolean is) {
        mSharedPreferences.edit().putBoolean("IsLocaleEn", is).apply();
    }

}
