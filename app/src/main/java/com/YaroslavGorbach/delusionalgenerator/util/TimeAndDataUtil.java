package com.YaroslavGorbach.delusionalgenerator.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeAndDataUtil {

    public static String getTimeAgo(long duration) {
        Date now = new Date();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - duration);
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - duration);
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - duration);

        if (seconds < 60) {
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

    public static String getFileDuration(File file) {
        double duration = file.getAbsoluteFile().length() / 2f;
        long durationL = (long) duration;
        return String.format(Locale.US, "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(durationL),
                TimeUnit.MILLISECONDS.toMinutes(durationL) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(durationL) % TimeUnit.MINUTES.toSeconds(1));
    }

    public static String formatDD(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("dd", Locale.getDefault());
        return format.format(millis);
    }

    public static String formatRecord(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.getDefault());
        return format.format(millis);
    }

    public static long getDaysBetween(Date lastReview, Date current){
        long diff =  current.getTime() - lastReview.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static boolean isNewTrainingAllow(Date trainingDate, Date currentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM", Locale.getDefault());
        return !dateFormat.format(currentDate).equals(dateFormat.format(trainingDate));
    }

}
