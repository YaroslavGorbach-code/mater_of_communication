package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

@androidx.room.Database(entities = {Statistics.class, Training.class},  version = 21)
@TypeConverters({Database.NameToString.class, Database.ListToString.class})
public abstract class Database extends RoomDatabase {
    private static Database sInstance = null;
    public abstract StatisticsDao statisticsDao();
    public abstract TrainingDao dailyTrainingDao();

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
                            cv.put("days", 0);
                            cv.put("exercises", "null");
                            db.insert("Training", SQLiteDatabase.CONFLICT_REPLACE, cv);
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }


    public static class NameToString {
        @TypeConverter
        public static int fromNameToString(Exercise.Name name) {
            return name == null ? null : name.ordinal();
        }

        @TypeConverter
        public static Exercise.Name fromStringToName(int nameId) {
            return (Exercise.Name.values()[nameId]);
        }
    }

    public static class ListToString {
        @TypeConverter
        public static ArrayList<Exercise> fromString(String value) {
            return new Gson().fromJson(value, new TypeToken<ArrayList<Exercise>>() {}.getType());
        }

        @TypeConverter
        public static String fromArrayList(ArrayList<Exercise> list) {
            return new Gson().toJson(list);
        }

    }
}
