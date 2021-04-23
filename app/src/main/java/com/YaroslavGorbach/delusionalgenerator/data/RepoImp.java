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

            if (files != null)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
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
                R.string.ex_linguistic_pyramids_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_lp,
                R.string.short_desc_linguistic_pyramids_1,
                R.string.short_desc_linguistic_pyramids_2,
                R.string.short_desc_linguistic_pyramids_3));

        mExercises.add(new ExModel(
                ExModel.Name.RAVEN_LOOK_LIKE_A_TABLE,
                R.string.ex_raven_look_like_a_table_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_raven,
                R.string.short_desc_raven_look_like_a_table));

        mExercises.add(new ExModel(
                ExModel.Name.STORYTELLER_IMPROVISER,
                R.string.ex_storyteller_improviser_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_promotional,
                R.string.short_desc_storyteller_improviser));

        mExercises.add(new ExModel(
                ExModel.Name.REMEMBER_ALL,
                R.string.ex_remember_all_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_memory,
                R.string.short_desc_remember_all));

        mExercises.add(new ExModel(
                ExModel.Name.WHAT_I_SEE_I_SING_ABOUT,
                R.string.ex_what_i_see_i_sing_about_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_person_with_microfon,
                R.string.short_desc_what_i_see_i_sing_about));

        mExercises.add(new ExModel(
                ExModel.Name.OTHER_ABBREVIATIONS,
                R.string.ex_other_abbreviations_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_letter,
                R.string.short_desc_other_abbreviations));

        mExercises.add(new ExModel(
                ExModel.Name.MAGIC_NAMING,
                R.string.ex_magic_naming_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_magic,
                R.string.short_desc_magic_naming));

        mExercises.add(new ExModel(
                ExModel.Name.BUYING_SELLING,
                R.string.ex_buying_selling_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_cart,
                R.string.short_desc_buying_selling));

        mExercises.add(new ExModel(
                ExModel.Name.ADVANCED_BINDING,
                R.string.ex_adjectives_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_cahain,
                R.string.short_desc_advanced_binding));

        mExercises.add(new ExModel(
                ExModel.Name.CO_AUTHORED_WITH_DAHL,
                R.string.ex_co_authored_with_dahl_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_pen,
                R.string.short_desc_co_authored_with_dahl));

        mExercises.add(new ExModel(
                ExModel.Name.RORSCHACH_TEST,
                R.string.ex_rorschach_test_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_butterfly,
                R.string.short_desc_rorschach_test));

        mExercises.add(new ExModel(
                ExModel.Name.WILL_NOT_BE_WORSE,
                R.string.ex_will_not_be_worse_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_thomb_down,
                R.string.short_desc_will_not_be_worse));

        mExercises.add(new ExModel(
                ExModel.Name.QUESTION_ANSWER,
                R.string.ex_question_answer_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_qestion,
                R.string.short_desc_question_answer));

        mExercises.add(new ExModel(
                ExModel.Name.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
                R.string.ex_raven_look_like_a_table_filings_desc,
                ExModel.Category.SPEAKING,
                R.drawable.ic_raven_2,
                R.string.short_desc_raven_look_like_a_table_filings));

        mExercises.add(new ExModel(
                ExModel.Name.NOUNS,
                R.string.ex_nouns_desc,
                ExModel.Category.VOCABULARY,
                R.drawable.ic_book_1,
                R.string.short_desc_nouns));

        mExercises.add(new ExModel(
                ExModel.Name.ADJECTIVES,
                R.string.ex_adjectives_desc,
                ExModel.Category.VOCABULARY,
                R.drawable.ic_book_2,
                R.string.short_desc_other_abbreviations));

        mExercises.add(new ExModel(
                ExModel.Name.VERBS,
                R.string.ex_verbs_desc,
                ExModel.Category.VOCABULARY,
                R.drawable.ic_book_3,
                R.string.short_desc_verbs
        ));

        mExercises.add(new ExModel(
                ExModel.Name.EASY_TONGUE_TWISTERS,
                R.string.ex_tongue_twisters_desc,
                ExModel.Category.TONGUE_TWISTER,
                R.drawable.ic_lips_2,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));

        mExercises.add(new ExModel(
                ExModel.Name.DIFFICULT_TONGUE_TWISTERS,
                R.string.ex_tongue_twisters_desc,
                ExModel.Category.TONGUE_TWISTER,
                R.drawable.ic_lips_1,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));

        mExercises.add(new ExModel(
                ExModel.Name.VERY_DIFFICULT_TONGUE_TWISTERS,
                R.string.ex_tongue_twisters_desc,
                ExModel.Category.TONGUE_TWISTER,
                R.drawable.ic_lips_3,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));
    }

}
