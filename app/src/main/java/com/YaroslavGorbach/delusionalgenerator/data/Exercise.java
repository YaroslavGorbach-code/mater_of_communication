package com.YaroslavGorbach.delusionalgenerator.data;

import com.YaroslavGorbach.delusionalgenerator.R;

public class Exercise {
    private final Name name;
    private final int imageId;
    private final Category category;
    public Type type = Type.COMMON;
    public int aim;
    public int done;
    private final int descriptionId;
    private final int[] shortDescIds;

    public Name getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public Category getCategory() {
        return category;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public int[] getShortDescIds() {
        return shortDescIds;
    }

    public int getProgress(){
        if (done!= 0 && done % aim == 0){
            return 100;
        }else {
            return (done % aim) * 10;
        }
    }

    public Exercise(
            Name name,
            int descriptionId,
            Category category,
            int imageId,
            int...shortDescIds
    ){
        this.name = name;
        this.category = category;
        this.shortDescIds = shortDescIds;
        this.descriptionId = descriptionId;
        this.imageId = imageId;
    }

    public enum Type{
        COMMON,
        DAILY
    }

    public enum Category {
        SPEAKING("Для развития комуникабельности"),
        VOCABULARY("Для увеличения словарного запаса"),
        TONGUE_TWISTER("Для развития четкости речи");
        private final String name;
        public String getName(){ return name; }
        Category(String name){
            this.name = name;
        }
    }

    public enum Name {
        LINGUISTIC_PYRAMIDS(R.string.ex_linguistic_pyramids_name),
        RAVEN_LOOK_LIKE_A_TABLE(R.string.ex_raven_look_like_a_table_name),
        STORYTELLER_IMPROVISER(R.string.ex_storyteller_improviser_name),
        ADVANCED_BINDING(R.string.ex_advanced_binding_name),
        WHAT_I_SEE_I_SING_ABOUT(R.string.ex_what_i_see_i_sing_about_name),
        OTHER_ABBREVIATIONS(R.string.ex_other_abbreviations_name),
        MAGIC_NAMING(R.string.ex_magic_naming_name),
        BUYING_SELLING(R.string.ex_buying_selling_name),
        REMEMBER_ALL(R.string.ex_remember_all_name),
        CO_AUTHORED_WITH_DAHL(R.string.ex_co_authored_with_dahl_name),
        RORSCHACH_TEST(R.string.ex_rorschach_test_name),
        WILL_NOT_BE_WORSE(R.string.ex_will_not_be_worse_name),
        QUESTION_ANSWER(R.string.ex_question_answer_name),
        RAVEN_LOOK_LIKE_A_TABLE_FILINGS(R.string.ex_raven_look_like_a_table_filings_name),
        NOUNS(R.string.ex_nouns_name),
        ADJECTIVES(R.string.ex_adjectives_name),
        VERBS(R.string.ex_verbs_name),
        EASY_TONGUE_TWISTERS(R.string.ex_easy_tongue_twisters_name),
        DIFFICULT_TONGUE_TWISTERS(R.string.ex_difficult_tongue_twisters_name),
        VERY_DIFFICULT_TONGUE_TWISTERS(R.string.ex_very_difficult_tongue_twisters_name);
        private final int nameId;
        public int getNameId(){ return nameId; }
        Name(int nameId){
            this.nameId = nameId;
        }
    }
}
