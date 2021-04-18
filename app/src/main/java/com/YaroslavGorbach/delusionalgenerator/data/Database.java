package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Statistics.class},  version = 2)
public abstract class Database extends RoomDatabase {
    private static Database sInstance = null;
    public abstract StatisticsDao statisticsDao();

    public static Database getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context, Database.class, "db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

}
