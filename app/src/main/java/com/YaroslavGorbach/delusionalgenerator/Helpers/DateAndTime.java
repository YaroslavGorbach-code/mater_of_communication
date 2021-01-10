package com.YaroslavGorbach.delusionalgenerator.Helpers;

import android.content.Context;
import android.os.SystemClock;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateAndTime {
    /*Метод используеться для ведения статистики*/
    static public void insertDataToStatistic(Context context, int exId, int value, long baseTime){
        //получаем текущую дату
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String date = dateFormat.format(currentDate);
        long time = ((SystemClock.elapsedRealtime() - baseTime)/1000) / 60;
        Repo.getInstance(context).insertDateAndTime(exId, date, time);
        Repo.getInstance(context).insertDateAndValue(exId, date, value);
    }

}