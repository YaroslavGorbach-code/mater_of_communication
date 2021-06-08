package com.YaroslavGorbach.delusionalgenerator.data.local.pref.common;

public interface CommonPrefStorage {
    boolean getFirstOpen();
    void setFirstOpen(boolean firstOpen);
    void setInterstitialAdCount(int count);
    int getInterstitialAdCount();
    void setNightMod(boolean nightMod);
    boolean getNightMod();
    boolean getAdIsAllow();
    void setAdIsAllow(boolean isAdRemoved);
    void setTimeLastReviewAsc(long time);
    long getTimeLastReviewAsc();
    boolean getIsEnLanguage();
    void setLocaleIsEn(boolean is);
}
