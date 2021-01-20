package com.YaroslavGorbach.delusionalgenerator.Helpers;

import android.text.format.DateUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Time {

    public String getTimeAgo(long duration) {
        Date now = new Date();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - duration);
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - duration);
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - duration);

        if(seconds < 60){
            return "just now";
        } else if (minutes == 1) {
            return "a minute ago";
        } else if (minutes > 1 && minutes < 60) {
            return minutes + " minutes ago";
        } else if (hours == 1) {
            return "an hour ago";
        } else if (hours > 1 && hours < 24) {
            return hours + " hours ago";
        } else if (days == 1) {
            return "a day ago";
        } else {
            return days + " days ago";
        }

    }

    public String getFileDuration(File file){
        long durationInMilSeconds = file.length();
        String hms = String.format(Locale.US,"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(durationInMilSeconds),
                TimeUnit.MILLISECONDS.toMinutes(durationInMilSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(durationInMilSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(durationInMilSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(durationInMilSeconds)));
       return hms;
    }

}
