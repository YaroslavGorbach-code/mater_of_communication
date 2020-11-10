package com.YaroslavGorbach.delusionalgenerator.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.YaroslavGorbach.delusionalgenerator.Database.Daos.Exercise_dao;
import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;

import java.util.Date;

@androidx.room.Database(entities = {Exercise.class},  version = 2)
public abstract class Database extends RoomDatabase {

    private static Database sInstance;

    public abstract Exercise_dao exercise_dao();


    public static synchronized Database getInstance(Context context){

        if (sInstance == null){

            sInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "counter.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(rdc)
                    .build();
        }

        return sInstance;

    }


    private static RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            Exercise_dao mDao;
            mDao = sInstance.exercise_dao();
            new Thread(() -> {
                mDao.insert(new Exercise(1,"Лингвистические пирамиды",1,0, 0));
                mDao.insert(new Exercise(2,"Чем ворон похож на стол",1,0, 1));
                mDao.insert(new Exercise(3,"Чем ворон похож на стул (чувства)",1,0, 0));
                mDao.insert(new Exercise(4,"Продвинутое связывание",1,0, 1));
                mDao.insert(new Exercise(5,"О чем вижу, о том и пою",1,0, 0));
                mDao.insert(new Exercise(6,"Другие варианты сокращений",1,0, 0));
                mDao.insert(new Exercise(7,"Волшебный нейминг",1,0, 0));
                mDao.insert(new Exercise(8,"Купля - продажа",1,0, 0));
                mDao.insert(new Exercise(9,"Вспомнить все",1,0, 0));
                mDao.insert(new Exercise(10,"В соавторстве с Далем",1,0, 0));
                mDao.insert(new Exercise(11,"Тест Роршаха",1,0, 0));
                mDao.insert(new Exercise(12,"Хуже уже не будет",1,0, 0));


                mDao.insert(new Exercise(20,"Существительные",2,0, 0));
                mDao.insert(new Exercise(21,"Прилагательные",2,0, 0));
                mDao.insert(new Exercise(22,"Глаголы",2,0, 0));

                mDao.insert(new Exercise(30,"Простые",3,0, 1));
                mDao.insert(new Exercise(31,"Сложные",3,0, 0));
                mDao.insert(new Exercise(32,"Очень сложные",3,0, 0));



            }).start();
        }

        public void onOpen(SupportSQLiteDatabase db) {
            // do something every time database is open
        }
    };
}
