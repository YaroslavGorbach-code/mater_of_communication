package com.YaroslavGorbach.delusionalgenerator.data;

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
}