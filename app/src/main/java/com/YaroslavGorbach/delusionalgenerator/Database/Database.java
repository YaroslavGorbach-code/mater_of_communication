package com.YaroslavGorbach.delusionalgenerator.Database;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.YaroslavGorbach.delusionalgenerator.Database.Daos.Exercise_dao;
import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.R;

import java.util.Date;

@androidx.room.Database(entities = {Exercise.class},  version = 3)
public abstract class Database extends RoomDatabase {
    private static Database sInstance;
    public abstract Exercise_dao exercise_dao();

    public static synchronized Database getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "counter.db")
                    .addMigrations(MIGRATION_2_3)
                    .addCallback(rdc)
                    .build();
        }
        return sInstance;
    }

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            ContentValues cv = new ContentValues();
            cv.put("name", "Простые скороговорки");
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 30", null );
            cv.put("name", "Сложные скороговорки");
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 31", null );
            cv.put("name", "Очень сложные скороговорки");
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 32", null );
     }
    };

    private static final RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            Exercise_dao mDao;
            mDao = sInstance.exercise_dao();
            new Thread(() -> {
                mDao.insert(new Exercise(1,"Лингвистические пирамиды",1, R.drawable.ex1_backgraund, 0));
                mDao.insert(new Exercise(2,"Чем ворон похож на стол",1,R.drawable.ex2_backgraund, 0));
                mDao.insert(new Exercise(3,"Чем ворон похож на стул (чувства)",1,R.drawable.ex3_backgraund, 0));
                mDao.insert(new Exercise(4,"Продвинутое связывание",1,R.drawable.ex4_backgraund, 0));
                mDao.insert(new Exercise(5,"О чем вижу, о том и пою",1,R.drawable.ex5_backgraund, 0));
                mDao.insert(new Exercise(6,"Другие варианты сокращений",1,R.drawable.ex6_backgraund, 0));
                mDao.insert(new Exercise(7,"Волшебный нейминг",1,R.drawable.ex7_backgraund, 0));
                mDao.insert(new Exercise(8,"Купля - продажа",1,R.drawable.ex8_backgraund, 0));
                mDao.insert(new Exercise(9,"Вспомнить все",1,R.drawable.ex9_backgraund, 0));
                mDao.insert(new Exercise(10,"В соавторстве с Далем",1,R.drawable.ex10_backgaraund, 0));
                mDao.insert(new Exercise(11,"Тест Роршаха",1,R.drawable.ex11_backgraund, 0));
                mDao.insert(new Exercise(12,"Хуже уже не будет",1,R.drawable.ex12_backgraund, 0));


                mDao.insert(new Exercise(20,"Существительные",2,R.drawable.ex13_backgraund, 0));
                mDao.insert(new Exercise(21,"Прилагательные",2,R.drawable.ex14_backgraund, 0));
                mDao.insert(new Exercise(22,"Глаголы",2,R.drawable.ex15_backgraund, 0));

                mDao.insert(new Exercise(30,"Простые скороговорки",3,R.drawable.ex16_backgraund, 0));
                mDao.insert(new Exercise(31,"Сложные скороговорки",3,R.drawable.ex17_backgraund, 0));
                mDao.insert(new Exercise(32,"Очень сложные скороговорки",3,R.drawable.ex18_backgraund, 0));

            }).start();
        }

        public void onOpen(SupportSQLiteDatabase db) {
            // do something every time database is open
        }
    };
}
