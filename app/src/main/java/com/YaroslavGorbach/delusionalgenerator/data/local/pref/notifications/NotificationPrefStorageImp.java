package com.YaroslavGorbach.delusionalgenerator.data.local.pref.notifications;

import android.content.Context;
import android.content.SharedPreferences;

public class NotificationPrefStorageImp implements NotificationPrefStorage {

    private final SharedPreferences mSharedPreferences;
    public NotificationPrefStorageImp(Context context){
        mSharedPreferences = context.getSharedPreferences("com.YaroslavGorbach.delusionalgenerator", Context.MODE_PRIVATE);

    }
    @Override
    public int getNotificationHour() {
        return mSharedPreferences.getInt("notificationHour", 12);
    }

    @Override
    public void setNotificationHour(int hour) {
        mSharedPreferences.edit().putInt("notificationHour", hour).apply();
    }

    @Override
    public int getNotificationMinute() {
        return mSharedPreferences.getInt("notificationMinute", 30);
    }

    @Override
    public void setNotificationMinute(int minute) {
        mSharedPreferences.edit().putInt("notificationMinute", minute).apply();
    }

    @Override
    public String getNotificationText() {
        return mSharedPreferences.getString("notificationText", "Time to speak");
    }

    @Override
    public void setNotificationText(String text) {
        mSharedPreferences.edit().putString("notificationText", text).apply();
    }

    @Override
    public boolean getNotificationIsAllow() {
        return mSharedPreferences.getBoolean("notificationIsAllow", true);
    }

    @Override
    public void setNotificationIsAllow(boolean isEnable) {
        mSharedPreferences.edit().putBoolean("notificationIsAllow", isEnable).apply();
    }

}
