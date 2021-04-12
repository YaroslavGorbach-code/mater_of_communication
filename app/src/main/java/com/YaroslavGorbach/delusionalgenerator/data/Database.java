package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.ContentValues;
import android.content.Context;

import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.YaroslavGorbach.delusionalgenerator.R;

@androidx.room.Database(entities = {
        Exercise.class},
        version = 9)

public abstract class Database extends RoomDatabase {
    private static Database sInstance;
    public abstract Exercise_dao exercise_dao();

    public static synchronized Database getInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "counter.db")
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .addMigrations(MIGRATION_4_5)
                    .addMigrations(MIGRATION_5_6)
                    .addMigrations(MIGRATION_6_7)
                    .addMigrations(MIGRATION_7_8)
                    .addCallback(rdc)
                    .fallbackToDestructiveMigration()
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
            cv.clear();
            cv.put("name", "Сложные скороговорки");
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 31", null );
            cv.clear();
            cv.put("name", "Очень сложные скороговорки");
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 32", null );
     }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
             database.execSQL("ALTER TABLE exercises_table ADD COLUMN sort_order INTEGER DEFAULT 0 NOT NULL");
              ContentValues cv = new ContentValues();
            Exercise_dao exDao;
            exDao = sInstance.exercise_dao();
            new Thread(() -> {
                exDao.insert(new Exercise(13,"Вопрос - ответ",1,R.drawable.ex13_backgraund_v_2, 0,13));
            }).start();
            cv.put("sort_order", "1");
            cv.put("pic", R.drawable.ex1_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 1", null );
            cv.clear();
            cv.put("sort_order", "2");
            cv.put("pic", R.drawable.ex2_backgraund_v_3);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 2", null );
            cv.clear();
            cv.put("sort_order", "3");
            cv.put("pic", R.drawable.ex3_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 3", null );
            cv.clear();
            cv.put("sort_order", "4");
            cv.put("pic", R.drawable.ex4_backgraund_v_3);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 4", null );
            cv.clear();
            cv.put("sort_order", "5");
            cv.put("pic", R.drawable.ex5_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 5", null );
            cv.clear();
            cv.put("sort_order", "6");
            cv.put("pic", R.drawable.ex6_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 6", null );
            cv.clear();
            cv.put("sort_order", "7");
            cv.put("pic", R.drawable.ex7_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 7", null );
            cv.clear();
            cv.put("sort_order", "8");
            cv.put("pic", R.drawable.ex8_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 8", null );
            cv.clear();
            cv.put("sort_order", "9");
            cv.put("pic", R.drawable.ex9_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 9", null );
            cv.clear();
            cv.put("sort_order", "10");
            cv.put("pic", R.drawable.ex10_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 10", null );
            cv.clear();
            cv.put("sort_order", "11");
            cv.put("pic", R.drawable.ex11_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 11", null );
            cv.clear();
            cv.put("sort_order", "12");
            cv.put("pic", R.drawable.ex12_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 12", null );
            cv.clear();
            cv.put("sort_order", "20");
            cv.put("pic", R.drawable.ex20_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 20", null );
            cv.clear();
            cv.put("sort_order", "21");
            cv.put("pic", R.drawable.ex21_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 21", null );
            cv.clear();
            cv.put("sort_order", "22");
            cv.put("pic", R.drawable.ex22_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 22", null );
            cv.clear();
            cv.put("sort_order", "30");
            cv.put("pic", R.drawable.ex30_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 30", null );
            cv.clear();
            cv.put("sort_order", "31");
            cv.put("pic", R.drawable.ex31_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 31", null );
            cv.clear();
            cv.put("sort_order", "32");
            cv.put("pic", R.drawable.ex32_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 32", null );
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            ContentValues cv = new ContentValues();
            cv.put("pic", R.drawable.ex3_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 3", null );
            cv.clear();
            cv.put("pic", R.drawable.ex4_backgraund_v_3);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 4", null );
            cv.clear();
            cv.put("pic", R.drawable.ex5_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 5", null );
            cv.clear();
            cv.put("pic", R.drawable.ex6_backgraund_v_2);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 6", null );
        }

    };

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            ContentValues cv = new ContentValues();
            Exercise_dao exDao;
            exDao = sInstance.exercise_dao();
            new Thread(() -> {
                exDao.insert(new Exercise(14,"Рассказчик - импровизатор",1,R.drawable.ex14_backgraund_v_2, 0,3));
            }).start();

            cv.put("pic", R.drawable.ex4_backgraund_v_3);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 4", null );
            cv.clear();
            cv.put("sort_order", 14);
            database.update("exercises_table", OnConflictStrategy.REPLACE, cv, "id = 3", null );
        }

    };

    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE description_category_1_table (" +
                    "exId INTEGER PRIMARY KEY NOT NULL," +
                    "aim TEXT," +
                    "description TEXT," +
                    "example TEXT," +
                    "period TEXT) " );

            database.execSQL("CREATE TABLE description_category_2_table (" +
                    "exId INTEGER PRIMARY KEY NOT NULL," +
                    "aim TEXT," +
                    "description TEXT," +
                    "period TEXT) " );

            database.execSQL("CREATE TABLE description_category_3_table (" +
                    "exId INTEGER PRIMARY KEY NOT NULL," +
                    "aim TEXT," +
                    "description_part_1 TEXT," +
                    "description_part_2 TEXT," +
                    "description_part_3 TEXT," +
                    "description_part_4 TEXT," +
                    "description_part_5 TEXT) " );

        }
    };

    static final Migration MIGRATION_7_8 = new Migration(7, 8) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Exercise_dao exDao;
            exDao = sInstance.exercise_dao();
            new Thread(() -> {
                exDao.update(new Exercise(1,"Лингвистические пирамиды",1, R.drawable.ex1_backgraund_v_2, 0, 1));
                exDao.update(new Exercise(2,"Чем ворон похож на стол",1,R.drawable.ex2_backgraund_v_3, 0, 2));
                exDao.update(new Exercise(14,"Рассказчик - импровизатор",1,R.drawable.ex14_backgraund_v_2, 0,3));
                exDao.update(new Exercise(4,"Продвинутое связывание",1,R.drawable.ex4_backgraund_v_3, 0,4));
                exDao.update(new Exercise(5,"О чем вижу, о том и пою",1,R.drawable.ex5_backgraund_v_2, 0,5));
                exDao.update(new Exercise(6,"Другие варианты сокращений",1,R.drawable.ex6_backgraund_v_2, 0,6));
                exDao.update(new Exercise(7,"Волшебный нейминг",1,R.drawable.ex7_backgraund_v_2, 0,7));
                exDao.update(new Exercise(8,"Купля - продажа",1,R.drawable.ex8_backgraund_v_2, 0,8));
                exDao.update(new Exercise(9,"Вспомнить все",1,R.drawable.ex9_backgraund_v_2, 0,9));
                exDao.update(new Exercise(10,"В соавторстве с Далем",1,R.drawable.ex10_backgraund_v_2,0,10));
                exDao.update(new Exercise(11,"Тест Роршаха",1,R.drawable.ex11_backgraund_v_2, 0,11));
                exDao.update(new Exercise(12,"Хуже уже не будет",1,R.drawable.ex12_backgraund_v_2, 0,12));
                exDao.update(new Exercise(13,"Вопрос - ответ",1,R.drawable.ex13_backgraund_v_2, 0,13));
                exDao.update(new Exercise(3,"Чем ворон похож на стул (чувства)",1,R.drawable.ex3_backgraund_v_2, 0,14));

                exDao.update(new Exercise(20,"Существительные",2,R.drawable.ex20_backgraund_v_2, 0,1));
                exDao.update(new Exercise(21,"Прилагательные",2,R.drawable.ex21_backgraund_v_2, 0,2));
                exDao.update(new Exercise(22,"Глаголы",2,R.drawable.ex22_backgraund_v_2, 0,3));

                exDao.update(new Exercise(30,"Простые скороговорки",3,R.drawable.ex30_backgraund_v_2, 0,1));
                exDao.update(new Exercise(31,"Сложные скороговорки",3,R.drawable.ex31_backgraund_v_2, 0,2));
                exDao.update(new Exercise(32,"Очень сложные скороговорки",3,R.drawable.ex32_backgraund_v_2, 0,3));
            }).start();
        }
    };

    private static final RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            Exercise_dao exDao;
            exDao = sInstance.exercise_dao();
            new Thread(() -> {
                Data.insertExercisesCategory_1(exDao);
                Data.insertExercisesCategory_2(exDao);
                Data.insertExercisesCategory_3(exDao);
            }).start();
        }

        public void onOpen(SupportSQLiteDatabase db) {
            // do something every time database is open
        }
    };
}
