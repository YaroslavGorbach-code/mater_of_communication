package com.YaroslavGorbach.delusionalgenerator.data.room;

import androidx.room.TypeConverter;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;

public class Converters {
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

    public static class DateToLong {
        @TypeConverter
        public static Date fromLong(long value) {
            Date date = new Date();
            date.setTime(value);
            return date;
        }

        @TypeConverter
        public static long fromDate(Date date) {
            return date.getTime();
        }

    }
}
