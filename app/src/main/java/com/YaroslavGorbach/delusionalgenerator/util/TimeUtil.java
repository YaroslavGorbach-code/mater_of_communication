package com.YaroslavGorbach.delusionalgenerator.util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public static String getTimeAgo(long duration) {
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

    public static String getFileDuration(File file){
        double duration = file.getAbsoluteFile().length()/2f;
        long durationL = (long) duration;
        return String.format(Locale.US,"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(durationL),
                TimeUnit.MILLISECONDS.toMinutes(durationL) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(durationL) % TimeUnit.MINUTES.toSeconds(1));
    }

    public static String formatDD(long millis){
        SimpleDateFormat format = new SimpleDateFormat("dd", Locale.getDefault());
        return format.format(millis);
    }
    public static String formatRecord(long millis){
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.getDefault());
        return format.format(millis);
    }

    public static long getDaysBetween(long start, long end){
        return TimeUnit.DAYS.convert(end - start, TimeUnit.MILLISECONDS);
    }

}
