package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RepoImp implements Repo {
    private final List<ExModel> mExercises = new ArrayList<>();
    private final Database mDatabase;

    public RepoImp(Database database) {
        mDatabase = database;

        mExercises.add(new ExModel(
                0,
                ExModel.Name.LINGUISTIC_PYRAMIDS,
                R.string.description_lp,
                ExModel.Category.SPEAKING,
                R.drawable.ic_list_test,
                R.string.short_desc_lp_1,
                R.string.short_desc_lp_2,
                R.string.short_desc_lp_3));

        mExercises.add(new ExModel(
                1,
                ExModel.Name.EASY_TONGUE_TWISTERS,
                R.string.description_lp,
                ExModel.Category.TONGUE_TWISTER,
                R.drawable.ic_list_test,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));

        mExercises.add(new ExModel(
                2,
                ExModel.Name.VERBS,
                R.string.description_lp,
                ExModel.Category.VOCABULARY,
                R.drawable.ic_list_test,
                R.string.short_desc_verbs
        ));
    }


    @Override
    public List<ExModel> getExercises() {
        return mExercises;
    }

    @Override
    public ExModel getExercise(int id) {
        return mExercises.get(id);
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
    public List<Statistics> getStatistics(int exId) {
        List<Statistics> data = mDatabase.statisticsDao().getData(exId);
        if (data.isEmpty()){
            addStatistics(new Statistics(exId, 10, 0));
        }
        return data;
    }

    @Override
    public void addStatistics(Statistics statistics) {
        mDatabase.statisticsDao().insert(statistics);
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
}
