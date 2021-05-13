package com.YaroslavGorbach.delusionalgenerator.data.local;

public interface SharedPrefStorage {
    boolean getFirstOpen();
    void setFirstOpen(boolean firstOpen);
    int getNotificationHour();
    void setNotificationHour(int hour);
    int getNotificationMinute();
    void setNotificationMinute(int minute);
    String getNotificationText();
    void setNotificationText(String text);
    boolean getNotificationIsAllow();
    void setNotificationIsAllow(boolean isEnable);
    void setInterstitialAdCount(int count);
    int getInterstitialAdCount();
    void setNightMod(boolean nightMod);
    boolean getNightMod();
    boolean getAdIsAllow();
    void setAdIsAllow(boolean isAdRemoved);

}
