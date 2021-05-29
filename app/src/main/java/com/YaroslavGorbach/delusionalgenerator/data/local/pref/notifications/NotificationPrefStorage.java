package com.YaroslavGorbach.delusionalgenerator.data.local.pref.notifications;

public interface NotificationPrefStorage {
    int getNotificationHour();
    void setNotificationHour(int hour);
    int getNotificationMinute();
    void setNotificationMinute(int minute);
    String getNotificationText();
    void setNotificationText(String text);
    boolean getNotificationIsAllow();
    void setNotificationIsAllow(boolean isEnable);
}
