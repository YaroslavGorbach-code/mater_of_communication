package com.YaroslavGorbach.delusionalgenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Repo extends SQLiteOpenHelper {




    public interface Listener{
        void onDataChange();
    }

    private static Repo sInstance;
    public static Repo getInstance(Context context){

        if (sInstance==null){
            sInstance = new Repo(context);
        }
        return sInstance;
    }
    private final Set<Listener> mListeners = new HashSet<>();
    public static final String DB_NAME = "generator.db";
    public static final int VERSION = 10;

    //S means Sessions
    public static final String TABLE_NAME_S = "books";
    public static final String ID_S = "id";
    public static final String ID_EX_ID = "idEx";
    public static final String DATE_S = "date";
    public static final String TIME_S = "time";

    public static final String TABLE_NAME_T = "thems";
    public static final String ID_T = "id";
    public static final String STATE_T = "state";
    public static final String COLOR_T = "color";

    public static final String TABLE_NAME_W = "worldsCount";
    public static final String ID_W = "id";
    public static final String ID_EX_W = "idEx";
    public static final String DATE_W = "date";
    public static final String COUNT_W = "count";


    public static final String CREATE_SQL_S = "CREATE TABLE " + TABLE_NAME_S + " (" +
            ID_S + " INTEGER PRIMARY KEY, " +
            ID_EX_ID + " INTEGER NOT NULL, " +
            DATE_S + " TEXT NOT NULL, " +
            TIME_S + " INTEGER NOT NULL " +
            " );";

    public static final String CREATE_SQL_T = "CREATE TABLE " + TABLE_NAME_T + " (" +
            ID_T + " INTEGER PRIMARY KEY, " +
            STATE_T + " INTEGER NOT NULL, " +
            COLOR_T + " TEXT NOT NULL " +
            " );";

    public static final String CREATE_SQL_W = "CREATE TABLE " + TABLE_NAME_W + " (" +
            ID_W + " INTEGER PRIMARY KEY, " +
            ID_EX_W + " INTEGER NOT NULL, " +
            DATE_W + " TEXT NOT NULL, " +
            COUNT_W + " INTEGER " +
            " );";

    private Repo(Context context){
        super(context,DB_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL_S);
        db.execSQL(CREATE_SQL_T);
        addThemes(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion == 9 && newVersion == 10){
            db.execSQL("DROP TABLE IF EXISTS worldsCount" );
            db.execSQL(CREATE_SQL_W);
        }


    }

    public void insertDateAndTime(int idEx, String date, float time) {
        ContentValues cv = new ContentValues();
        cv.put(ID_EX_ID, idEx);
        cv.put(DATE_S, date);
        cv.put(TIME_S, time);
        getWritableDatabase().insert(TABLE_NAME_S,null,cv);

    }

    public void insertDateAndCountWorlds(int idEx, String date, int worldsCount) {
        ContentValues cv = new ContentValues();
        cv.put(ID_EX_W, idEx);
        cv.put(DATE_W, date);
        cv.put(COUNT_W, worldsCount);
        getWritableDatabase().insert(TABLE_NAME_W,null,cv);

    }

    public int getMaxWorldCount(int idEx){
        String[] cols = {ID_EX_W, DATE_W, COUNT_W};
        int maxWorldCount = 0;
        Cursor c = getReadableDatabase().query(TABLE_NAME_W, cols, ID_EX_W + "=" + idEx, null,
                null, null, null);

        while (c.moveToNext()){
                if (c.getInt(2) > maxWorldCount){
                    maxWorldCount = c.getInt(2);
                }
        }

        c.close();
        return maxWorldCount;
    }

    public ArrayList<BarEntry> getEntriesTime(int idEx){
        ArrayList<BarEntry> entries = new ArrayList<>();
        int index = 0;

        String[] colons = {ID_S, ID_EX_ID, DATE_S, TIME_S};
       Cursor c = getReadableDatabase().query(TABLE_NAME_S, colons, ID_EX_ID + "=" + idEx, null,
                null,null, null);

      while (c.moveToNext()){
          entries.add(new BarEntry(c.getFloat(3), index));
          index++;
      }

         c.close();
        return entries;

    }

    public ArrayList<BarEntry> getEntriesWorldCount(int idEx){
        ArrayList<BarEntry> entries = new ArrayList<>();
        int index = 0;

        String[] cols = {ID_EX_W, DATE_W, COUNT_W};
        Cursor c = getReadableDatabase().query(TABLE_NAME_W, cols, ID_EX_W + "=" + idEx, null,
                null,null, null);

        while (c.moveToNext()){
            entries.add(new BarEntry(c.getFloat(2), index));
            index++;
        }

        c.close();
        return entries;

    }

    public ArrayList<String> getTimeLabels(int idEx){
        String[] colons = {ID_S, ID_EX_ID, DATE_S, TIME_S};
        ArrayList<String> labels = new ArrayList<String>();

        Cursor c = getReadableDatabase().query(TABLE_NAME_S, colons, ID_EX_ID + "=" + idEx, null,
                null, null, null);

        while (c.moveToNext()){

            labels.add(c.getString(2));

        }
        c.close();
        return labels;
    }

    public ArrayList<String> getWorldCountLabels(int idEx){
        String[] cols = {ID_EX_W, DATE_W, COUNT_W};
        ArrayList<String> labels = new ArrayList<String>();

        Cursor c = getReadableDatabase().query(TABLE_NAME_W, cols, ID_EX_W + "=" + idEx, null,
                null, null, null);

        while (c.moveToNext()){
            labels.add(c.getString(1));
        }
        c.close();
        return labels;
    }
    public void resetOldThemeState(){
        ContentValues cv = new ContentValues();
        cv.put(STATE_T, 0);
        getWritableDatabase().update(TABLE_NAME_T, cv,STATE_T + "=" + 1, null);
    }
    public void changeTheme(String color) {

        ContentValues cv = new ContentValues();
        cv.put(STATE_T, 1);
        getWritableDatabase().update(TABLE_NAME_T, cv, COLOR_T + "=" + "'" + color + "'", null );

    }

    public String getThemeState() {
        String color = null;
        String[] cols = {ID_T, STATE_T, COLOR_T};
        Cursor c = getReadableDatabase().query(TABLE_NAME_T, cols, STATE_T + "=" + 1, null,
        null, null, null);

        if(c.moveToFirst()){

            color = c.getString(2);
        }

        c.close();
        return color;


    }
    public void clearStatistic(int mIdEx) {
        getWritableDatabase().delete(TABLE_NAME_S,ID_EX_ID + "=" + mIdEx,null);
        getWritableDatabase().delete(TABLE_NAME_W,ID_EX_W + "=" + mIdEx,null);
        notifyChange();
    }

    private void notifyChange(){
        for(Listener listener : mListeners) listener.onDataChange();
    }

    //реализація шаблона наблюдатель
    public void addListener(Listener listener){

        mListeners.add(listener);
    }
    //реализація шаблона наблюдатель
    public void removeListener(Listener listener){
        mListeners.remove(listener);
    }

    private void addThemes(SQLiteDatabase db){

        ContentValues cv = new ContentValues();
        cv.put(STATE_T, 0);
        cv.put(COLOR_T, "blue");
        db.insert(TABLE_NAME_T, null, cv);

        cv.put(STATE_T, 0);
        cv.put(COLOR_T, "green");
        db.insert(TABLE_NAME_T, null, cv);

        cv.put(STATE_T, 1);
        cv.put(COLOR_T, "orange");
        db.insert(TABLE_NAME_T, null, cv);

        cv.put(STATE_T, 0);
        cv.put(COLOR_T, "purple");
        db.insert(TABLE_NAME_T, null, cv);

        cv.put(STATE_T, 0);
        cv.put(COLOR_T, "red");
        db.insert(TABLE_NAME_T, null, cv);


    }
}
