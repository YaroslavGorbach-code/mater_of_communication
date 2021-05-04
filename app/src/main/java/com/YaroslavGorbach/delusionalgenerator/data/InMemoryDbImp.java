package com.YaroslavGorbach.delusionalgenerator.data;
import android.content.res.Resources;
import com.YaroslavGorbach.delusionalgenerator.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class InMemoryDbImp implements InMemoryDb {
    private final List<Exercise> mExercises = new ArrayList<>();

    public InMemoryDbImp(){
        createExercises();
    }

    @Override
    public List<Exercise> getExercises() {
        return mExercises;
    }

    @Override
    public List<Exercise> getExercises(Exercise.Category category) {
        return Observable.fromIterable(mExercises)
                .filter(exercise -> exercise.getCategory() == category)
                .toList()
                .blockingGet();
    }

    @Override
    public Exercise getExercise(Exercise.Name name) {
        return Observable.fromIterable(mExercises)
                .filter(exModel -> exModel.getName() == name)
                .firstOrError()
                .blockingGet();
    }

    @Override
    public List<String> getWords(Repo.WordType type, Resources resources) {
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
            case QUESTIONS:
                return Arrays.asList(resources.getStringArray(R.array.questions));
            case EASY_T_T:
                return Arrays.asList(resources.getStringArray(R.array.twisters_easy));
            case DIFFICULT_T_T:
                return Arrays.asList(resources.getStringArray(R.array.twisters_hard));
            case VERY_DIFFICULT_T_T:
                return Arrays.asList(resources.getStringArray(R.array.twisters_very_hard));
        }
        return new ArrayList<>();
    }


    private void createExercises() {
        mExercises.add(new Exercise(
                Exercise.Name.LINGUISTIC_PYRAMIDS,
                R.string.ex_linguistic_pyramids_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_piramids,
                R.string.short_desc_linguistic_pyramids_1,
                R.string.short_desc_linguistic_pyramids_2,
                R.string.short_desc_linguistic_pyramids_3));

        mExercises.add(new Exercise(
                Exercise.Name.RAVEN_LOOK_LIKE_A_TABLE,
                R.string.ex_raven_look_like_a_table_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_raven,
                R.string.short_desc_raven_look_like_a_table));

        mExercises.add(new Exercise(
                Exercise.Name.STORYTELLER_IMPROVISER,
                R.string.ex_storyteller_improviser_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_promotional,
                R.string.short_desc_storyteller_improviser));

        mExercises.add(new Exercise(
                Exercise.Name.WHAT_I_SEE_I_SING_ABOUT,
                R.string.ex_what_i_see_i_sing_about_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_person_with_microfon,
                R.string.short_desc_what_i_see_i_sing_about));

        mExercises.add(new Exercise(
                Exercise.Name.OTHER_ABBREVIATIONS,
                R.string.ex_other_abbreviations_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_letter,
                R.string.short_desc_other_abbreviations));

        mExercises.add(new Exercise(
                Exercise.Name.MAGIC_NAMING,
                R.string.ex_magic_naming_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_magic,
                R.string.short_desc_magic_naming));

        mExercises.add(new Exercise(
                Exercise.Name.BUYING_SELLING,
                R.string.ex_buying_selling_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_cart,
                R.string.short_desc_buying_selling));

        mExercises.add(new Exercise(
                Exercise.Name.ADVANCED_BINDING,
                R.string.ex_advanced_binding_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_cahain,
                R.string.short_desc_advanced_binding));

        mExercises.add(new Exercise(
                Exercise.Name.CO_AUTHORED_WITH_DAHL,
                R.string.ex_co_authored_with_dahl_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_pen,
                R.string.short_desc_co_authored_with_dahl));

        mExercises.add(new Exercise(
                Exercise.Name.RORSCHACH_TEST,
                R.string.ex_rorschach_test_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_butterfly,
                R.string.short_desc_rorschach_test));

        mExercises.add(new Exercise(
                Exercise.Name.WILL_NOT_BE_WORSE,
                R.string.ex_will_not_be_worse_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_thomb_down,
                R.string.short_desc_will_not_be_worse));

        mExercises.add(new Exercise(
                Exercise.Name.QUESTION_ANSWER,
                R.string.ex_question_answer_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_qestion,
                R.string.short_desc_question_answer));

        mExercises.add(new Exercise(
                Exercise.Name.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
                R.string.ex_raven_look_like_a_table_filings_desc,
                Exercise.Category.SPEAKING,
                R.drawable.ic_raven_2,
                R.string.short_desc_raven_look_like_a_table_filings));

        mExercises.add(new Exercise(
                Exercise.Name.REMEMBER_ALL,
                R.string.ex_remember_all_desc,
                Exercise.Category.VOCABULARY,
                R.drawable.ic_memory,
                R.string.short_desc_remember_all));

        mExercises.add(new Exercise(
                Exercise.Name.NOUNS,
                R.string.ex_nouns_desc,
                Exercise.Category.VOCABULARY,
                R.drawable.ic_book_1,
                R.string.short_desc_nouns));

        mExercises.add(new Exercise(
                Exercise.Name.ADJECTIVES,
                R.string.ex_adjectives_desc,
                Exercise.Category.VOCABULARY,
                R.drawable.ic_book_2,
                R.string.short_desc_adjectives));

        mExercises.add(new Exercise(
                Exercise.Name.VERBS,
                R.string.ex_verbs_desc,
                Exercise.Category.VOCABULARY,
                R.drawable.ic_book_3,
                R.string.short_desc_verbs
        ));

        mExercises.add(new Exercise(
                Exercise.Name.EASY_TONGUE_TWISTERS,
                R.string.ex_tongue_twisters_desc,
                Exercise.Category.TONGUE_TWISTER,
                R.drawable.ic_lips_2,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));

        mExercises.add(new Exercise(
                Exercise.Name.DIFFICULT_TONGUE_TWISTERS,
                R.string.ex_tongue_twisters_desc,
                Exercise.Category.TONGUE_TWISTER,
                R.drawable.ic_lips_1,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));

        mExercises.add(new Exercise(
                Exercise.Name.VERY_DIFFICULT_TONGUE_TWISTERS,
                R.string.ex_tongue_twisters_desc,
                Exercise.Category.TONGUE_TWISTER,
                R.drawable.ic_lips_3,
                R.string.short_desc_tt_1,
                R.string.short_desc_tt_2,
                R.string.short_desc_tt_3,
                R.string.short_desc_tt_4,
                R.string.short_desc_tt_5));
    }

}
