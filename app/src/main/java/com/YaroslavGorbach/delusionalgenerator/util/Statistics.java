package com.YaroslavGorbach.delusionalgenerator.util;

import android.content.Context;
import android.os.SystemClock;

import com.YaroslavGorbach.delusionalgenerator.data.oldDataLayer.Repo_SQLite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Statistics {
    /*Метод используеться для ведения статистики*/
    static public void insertDataToStatistics(Context context, int exId, int value, long baseTime){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);
        long time = ((SystemClock.elapsedRealtime() - baseTime)/1000) / 60;
        Repo_SQLite.getInstance(context).insertDateAndTime(exId, date, time);
        Repo_SQLite.getInstance(context).insertDateAndValue(exId, date, value);
    }

}
