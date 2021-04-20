package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import com.YaroslavGorbach.delusionalgenerator.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RepoImp implements Repo {
    private final List<ExModel> mExercises = new ArrayList<>();
    private final Database mDatabase;
    private long mTimeStatisticsFirs;
    private long mTimeStatisticsLast;

    public RepoImp(Database database) {
        mDatabase = database;
        createExercises();
    }

    @Override
    public List<ExModel> getExercises() {
        return mExercises;
    }

    @Override
    public ExModel getExercise(ExModel.Name name) {
        for (int i = 0; i <mExercises.size()-1; i++) {
            if (mExercises.get(i).name == name)
                return mExercises.get(i);
        }
        return mExercises.get(mExercises.size()-1);
    }

    @Override
    public List<String> getWords(WordType type, Resources resources) {
        switch (type) {
            case ALIVE:
                return Arrays.asList(resources.getStringArray(R.array.Worlds_items_Alive));
            case NOT_ALIVE:
                return Arrays.asList(resources.getStringArray(R.array.Worlds_items_notAlive));
            case ABBREVIATION:
                return Arrays.asList(resources.getStringArray(R.array.Worlds_items_abbreviations));
            case FILLING:
                return Arrays.asList(resources.getStringArray(R.array.Worlds_items_filings));
            case LETTER:
                return Arrays.asList(resources.getStringArray(R.array.letters));
            case PROFESSIONS:
                return Arrays.asList(resources.getStringArray(R.array.professions));
            case TERMS:
                return Arrays.asList(resources.getStringArray(R.array.Terms));
            case EASY_T_T:
                return Arrays.asList(resources.getStringArray(R.array.twisters_easy));
            case DIFFICULT_T_T:
                return Arrays.asList(resources.getStringArray(R.array.twisters_hard));
            case VERY_DIFFICULT_T_T:
                return Arrays.asList(resources.getStringArray(R.array.twisters_very_hard));
        }
        return new ArrayList<>();
    }

    @Override
    public File[] getRecords(Context context) {

        File file = new File(context.getExternalFilesDir("/").getAbsolutePath());
        File[] files = file.listFiles();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (files != null)
                Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        }
        return files;
    }

    @Override
    public List<Statistics> getStatisticsLast(ExModel.Name name) {
        List<Statistics> data = mDatabase.statisticsDao().getStatisticsLast(name);
        if (data.isEmpty()){
            addStatistics(new Statistics(name, 5, 0));
            data = mDatabase.statisticsDao().getStatisticsLast(name);
        }
        initStatisticsTime(data);

        return data;
    }

    @Override
    public List<Statistics> getStatisticsNext(ExModel.Name name) {
        List<Statistics> data = mDatabase.statisticsDao().getStatisticsNext(name ,mTimeStatisticsFirs);
        if (!data.isEmpty()){
            initStatisticsTime(data);
        }
        return data;
    }

    @Override
    public List<Statistics> getStatisticsPrevious(ExModel.Name name) {
        List<Statistics> data = mDatabase.statisticsDao().getStatisticsPrevious(name, mTimeStatisticsLast);
        if (!data.isEmpty()){
            initStatisticsTime(data);
        }
        return data;
    }

    @Override
    public void addStatistics(Statistics statistics) {
        mDatabase.statisticsDao().insert(statistics);
    }

    private void initStatisticsTime(List<Statistics> data) {
        mTimeStatisticsFirs = data.get(0).dataTime;
        mTimeStatisticsLast = data.get(data.size() - 1).dataTime;
    }

    private void createExercises() {
        mExercises.add(new ExModel(
                ExModel.Name.LINGUISTIC_PYRAMIDS,
                R.string.description_lp,
                ExModel.Category.SPEAKING,
                R.drawable.ic_raven,
                R.string.short_desc_lp_1,
                R.string.short_desc_lp_2,
                R.string.short_desc_lp_3));

        mExercises.add(new ExModel(
                ExModel.Name.EASY_TONGUE_TWISTERS,
                R.string.description_lp,
                ExModel.Category.TONGUE_TWISTER,
                R.drawable.ic_lp,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));

        mExercises.add(new ExModel(
                ExModel.Name.VERBS,
                R.string.description_lp,
                ExModel.Category.VOCABULARY,
                R.drawable.ic_lp,
                R.string.short_desc_verbs
        ));
    }
}
