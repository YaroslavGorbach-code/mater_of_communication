package com.YaroslavGorbach.delusionalgenerator.data.local.room;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.YaroslavGorbach.delusionalgenerator.data.domain.Statistics;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Training;

@androidx.room.Database(entities = {Statistics.class, Training.class},  version = 21)
@TypeConverters({Converters.NameToString.class, Converters.ListToString.class, Converters.DateToLong.class})
public abstract class RoomDb extends RoomDatabase {
    private static RoomDb sInstance = null;
    public abstract StatisticsDao statisticsDao();
    public abstract TrainingDao dailyTrainingDao();

    public static RoomDb getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context, RoomDb.class, "db")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // room observable does not work if table is empty
                            ContentValues cv = new ContentValues();
                            cv.put("date", 0);
                            cv.put("days", 0);
                            cv.put("number", 0);
                            cv.put("exercises", "null");
                            db.insert("Training", SQLiteDatabase.CONFLICT_REPLACE, cv);
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

    
}
