package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

@androidx.room.Database(entities = {Statistics.class, DailyTrainingM.class},  version = 20)
@TypeConverters({Database.ConvertersNameToString.class, Database.ConvertersNameToArray.class})
public abstract class Database extends RoomDatabase {
    private static Database sInstance = null;
    public abstract StatisticsDao statisticsDao();
    public abstract DailyTrainingDao dailyTrainingDao();

    public static Database getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context, Database.class, "db")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // room observable does not work if table is empty
                            ContentValues cv = new ContentValues();
                            cv.put("id", 0);
                            cv.put("date", 0);
                            cv.put("progress", 0);
                            cv.put("days", 0);
                            cv.put("exercises", "null");
                            db.insert("DailyTrainingM", SQLiteDatabase.CONFLICT_REPLACE, cv);
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }


    public static class ConvertersNameToString {
        @TypeConverter
        public static int fromNameToString(Exercise.Name name) {
            return name == null ? null : name.ordinal();
        }

        @TypeConverter
        public static Exercise.Name fromStringToName(int nameId) {
            return (Exercise.Name.values()[nameId]);
        }
    }

    public static class ConvertersNameToArray {
        @TypeConverter
        public static ArrayList<DailyTrainingEx> fromString(String value) {
            return new Gson().fromJson(value, new TypeToken<ArrayList<DailyTrainingEx>>() {}.getType());
        }

        @TypeConverter
        public static String fromArrayList(ArrayList<DailyTrainingEx> list) {
            return new Gson().toJson(list);
        }

    }
}
