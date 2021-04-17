package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.YaroslavGorbach.delusionalgenerator.data.ExModel.Name.ADJECTIVES;

public class RepoImp implements Repo {
    private final List<ExModel> mExercises = new ArrayList<>();

    public RepoImp() {
        mExercises.add(new ExModel(
                0,
                ExModel.Name.LINGUISTIC_PYRAMIDS,
                "coming soon",
                ExModel.Category.SPEAKING,
                R.drawable.ic_list_test,
                "coming soon",
                "coming soon",
                "coming soon",
                "coming soon"));

        mExercises.add(new ExModel(
                1,
                ExModel.Name.EASY_TONGUE_TWISTERS,
                "coming soon",
                ExModel.Category.TONGUE_TWISTER,
                R.drawable.ic_list_test,
                "Проговаривайте текст медленно",
                "Беззвучно произнесите скороговорку. Движениями губ",
                "Произнесите текст в пол голоса, шепотом",
                "Произнесите вслух, громко, но все ещё медленно и четко",
                "Теперь пробуйте произносить текст в разных стилях, с разной интонацией, с разной скоростью"));

        mExercises.add(new ExModel(
                2,
                ExModel.Name.VERBS,
                "coming soon",
                ExModel.Category.VOCABULARY,
                R.drawable.ic_list_test,
                "coming soon",
                "coming soon",
                "coming soon",
                "coming soon"));
    }

    @Override
    public List<ExModel> getExercises() {
        return mExercises;
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
}
