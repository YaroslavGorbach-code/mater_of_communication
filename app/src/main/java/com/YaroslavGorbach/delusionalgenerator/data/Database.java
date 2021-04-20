package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


@androidx.room.Database(entities = {Statistics.class},  version = 6)
@TypeConverters(Database.Converters.class)
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

    public static class Converters {
        @TypeConverter
        public static int fromNameToString(ExModel.Name name) {
            return name == null ? null : name.ordinal();
        }

        @TypeConverter
        public static ExModel.Name fromStringToName(int nameId) {
            return (ExModel.Name.values()[nameId]);
        }
    }
}
