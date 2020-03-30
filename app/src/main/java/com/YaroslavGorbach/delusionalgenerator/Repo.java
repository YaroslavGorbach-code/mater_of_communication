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
    public static final int VERSION = 4;

    //S means Sessions
    public static final String TABLE_NAME_S = "books";
    public static final String ID_S = "id";
    public static final String ID_EX_ID = "idEx";
    public static final String DATE_S = "date";
    public static final String TIME_S = "time";

    public static final String CREATE_SQL_S = "CREATE TABLE " + TABLE_NAME_S + " (" +
            ID_S + " INTEGER PRIMARY KEY, " +
            ID_EX_ID + " INTEGER NOT NULL, " +
            DATE_S + " TEXT NOT NULL, " +
            TIME_S + " INTEGER NOT NULL " +
            " );";

    private Repo(Context context){
        super(context,DB_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL_S);
        //insertTESTDateAndTime(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_S);
        onCreate(db);

    }

    public void insertDateAndTime(int idEx, String date, float time) {
        ContentValues cv = new ContentValues();
        cv.put(ID_EX_ID, idEx);
        cv.put(DATE_S, date);
        cv.put(TIME_S, time);
        getWritableDatabase().insert(TABLE_NAME_S,null,cv);

    }

    public ArrayList<BarEntry> getEntries(int idEx){
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

    public ArrayList<String> getLabels(int idEx){
        String[] colons = {ID_S, ID_EX_ID, DATE_S, TIME_S};
        ArrayList<String> labels = new ArrayList<String>();

        Cursor c = getReadableDatabase().query(TABLE_NAME_S, colons, ID_EX_ID + "=" + idEx, null,
                null, null, null);

        while (c.moveToNext()){

            labels.add(c.getString(2));

        }

        return labels;
    }

    public void clearStatistic(int mIdEx) {
        getWritableDatabase().delete(TABLE_NAME_S,ID_EX_ID + "=" + mIdEx,null);
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
}
