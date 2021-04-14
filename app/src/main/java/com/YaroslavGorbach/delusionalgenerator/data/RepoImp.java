package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.YaroslavGorbach.delusionalgenerator.data.ExModel.Name.ADJECTIVES;

public class RepoImp implements Repo {
    private final List<ExModel> mExercises = new ArrayList<>();

     public RepoImp(){
         mExercises.add(new ExModel(
                 0,
                 ExModel.Name.LINGUISTIC_PYRAMIDS,
                 "coming soon",
                 ExModel.Category.SPEAKING,
                 R.drawable.ex1_backgraund_v_2,
                 "coming soon",
                 "coming soon",
                 "coming soon",
                 "coming soon"));

         mExercises.add(new ExModel(
                 1,
                 ExModel.Name.EASY_TONGUE_TWISTERS,
                 "coming soon",
                 ExModel.Category.TONGUE_TWISTER,
                 R.drawable.ex30_backgraund_v_2,
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
                 R.drawable.ex20_backgraund_v_2,
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

}
