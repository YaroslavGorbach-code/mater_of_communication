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

@androidx.room.Database(entities = {Exercise.class},  version = 4)
public abstract class Database extends RoomDatabase {
    private static Database sInstance;
    public abstract Exercise_dao exercise_dao();

    public static synchronized Database getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "counter.db")
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
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

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
             database.execSQL("ALTER TABLE exercises_table ADD COLUMN sort_order INTEGER DEFAULT 0 NOT NULL");
              ContentValues cv = new ContentValues();
            Exercise_dao mDao;
            mDao = sInstance.exercise_dao();
            new Thread(() -> {
                mDao.insert(new Exercise(13,"Вопрос - ответ",1,R.drawable.ex13_backgraund_v_2, 0,13));
            }).start();
            cv.put("sort_order", "1");
            cv.put("pic", R.drawable.ex1_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 1", null );
            cv.put("sort_order", "2");
            cv.put("pic", R.drawable.ex2_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 2", null );
            cv.put("sort_order", "3");
            cv.put("pic", R.drawable.ex3_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 3", null );
            cv.put("sort_order", "4");
            cv.put("pic", R.drawable.ex4_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 4", null );
            cv.put("sort_order", "5");
            cv.put("pic", R.drawable.ex5_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 5", null );
            cv.put("sort_order", "6");
            cv.put("pic", R.drawable.ex6_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 6", null );
            cv.put("sort_order", "7");
            cv.put("pic", R.drawable.ex7_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 7", null );
            cv.put("sort_order", "8");
            cv.put("pic", R.drawable.ex8_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 8", null );
            cv.put("sort_order", "9");
            cv.put("pic", R.drawable.ex9_backgraund_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 9", null );
            cv.put("sort_order", "10");
            cv.put("pic", R.drawable.ex10_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 10", null );
            cv.put("sort_order", "11");
            cv.put("pic", R.drawable.ex11_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 11", null );
            cv.put("sort_order", "12");
            cv.put("pic", R.drawable.ex12_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 12", null );

            cv.put("sort_order", "20");
            cv.put("pic", R.drawable.ex20_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 20", null );
            cv.put("sort_order", "21");
            cv.put("pic", R.drawable.ex21_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 21", null );
            cv.put("sort_order", "22");
            cv.put("pic", R.drawable.ex22_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 22", null );

            cv.put("sort_order", "30");
            cv.put("pic", R.drawable.ex30_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 30", null );
            cv.put("sort_order", "31");
            cv.put("pic", R.drawable.ex31_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 31", null );
            cv.put("sort_order", "32");
            cv.put("pic", R.drawable.ex32_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 32", null );

        }
    };

    private static final RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            Exercise_dao mDao;
            mDao = sInstance.exercise_dao();
            new Thread(() -> {
                mDao.insert(new Exercise(1,"Лингвистические пирамиды",1, R.drawable.ex1_backgraund_v_2, 0, 1));
                mDao.insert(new Exercise(2,"Чем ворон похож на стол",1,R.drawable.ex2_backgraund_v_3, 0, 2));
                mDao.insert(new Exercise(3,"Чем ворон похож на стул (чувства)",1,R.drawable.ex3_backgraund_v_2, 0,3));
                mDao.insert(new Exercise(4,"Продвинутое связывание",1,R.drawable.ex4_backgraund_v_2, 0,4));
                mDao.insert(new Exercise(5,"О чем вижу, о том и пою",1,R.drawable.ex5_backgraund_v_2, 0,5));
                mDao.insert(new Exercise(6,"Другие варианты сокращений",1,R.drawable.ex6_backgraund_v_2, 0,6));
                mDao.insert(new Exercise(7,"Волшебный нейминг",1,R.drawable.ex7_backgraund_v_2, 0,7));
                mDao.insert(new Exercise(8,"Купля - продажа",1,R.drawable.ex8_backgraund_v_2, 0,8));
                mDao.insert(new Exercise(9,"Вспомнить все",1,R.drawable.ex9_backgraund_2, 0,9));
                mDao.insert(new Exercise(10,"В соавторстве с Далем",1,R.drawable.ex10_backgraund_v_2,0,10));
                mDao.insert(new Exercise(11,"Тест Роршаха",1,R.drawable.ex11_backgraund_v_2, 0,11));
                mDao.insert(new Exercise(12,"Хуже уже не будет",1,R.drawable.ex12_backgraund_v_2, 0,12));
                mDao.insert(new Exercise(13,"Вопрос - ответ",1,R.drawable.ex13_backgraund_v_2, 0,13));

                mDao.insert(new Exercise(20,"Существительные",2,R.drawable.ex20_backgraund_v_2, 0,1));
                mDao.insert(new Exercise(21,"Прилагательные",2,R.drawable.ex21_backgraund_v_2, 0,2));
                mDao.insert(new Exercise(22,"Глаголы",2,R.drawable.ex22_backgraund_v_2, 0,3));

                mDao.insert(new Exercise(30,"Простые скороговорки",3,R.drawable.ex30_backgraund_v_2, 0,1));
                mDao.insert(new Exercise(31,"Сложные скороговорки",3,R.drawable.ex31_backgraund_v_2, 0,2));
                mDao.insert(new Exercise(32,"Очень сложные скороговорки",3,R.drawable.ex32_backgraund_v_2, 0,3));
            }).start();
        }

        public void onOpen(SupportSQLiteDatabase db) {
            // do something every time database is open
        }
    };
}
