package com.YaroslavGorbach.delusionalgenerator.data;

public class ExModel {
    public int id;
    public Name name;
    public int pic;
    public Category category;
    public int descriptionId;
    public String[] shortDesc;


    public ExModel(
            int id,
            Name name,
            int descriptionId,
            Category category,
            int pic,
            String...shortDesc
    ){
        this.id = id;
        this.name = name;
        this.category = category;
        this.shortDesc = shortDesc;
        this.descriptionId = descriptionId;
        this.pic = pic;
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
        LINGUISTIC_PYRAMIDS("Лингвистические пирамиды"),
        RAVEN_LOOK_LIKE_A_TABLE("Чем ворон похож на стол"),
        STORYTELLER_IMPROVISER("Рассказчик - импровизатор"),
        ADVANCED_BINDING("Продвинутое связывание"),
        WHAT_I_SEE_I_SING_ABOUT("О чем вижу, о том и пою"),
        OTHER_ABBREVIATIONS("Другие варианты сокращений"),
        MAGIC_NAMING("Волшебный нейминг"),
        BUYING_SELLING("Купля - продажа"),
        REMEMBER_ALL("Вспомнить все"),
        CO_AUTHORED_WITH_DAHL("В соавторстве с Далем"),
        RORSCHACH_TEST("Тест Роршаха"),
        WILL_NOT_BE_WORSE("Хуже уже не будет"),
        QUESTION_ANSWER("Вопрос - ответ"),
        RAVEN_LOOK_LIKE_A_TABLE_FILINGS("Чем ворон похож на стул (чувства)"),
        NOUNS("Существительные"),
        ADJECTIVES("Прилагательные"),
        VERBS("Глаголы"),
        EASY_TONGUE_TWISTERS("Простые скороговорки"),
        DIFFICULT_TONGUE_TWISTERS("Сложные скороговорки"),
        VERY_DIFFICULT_TONGUE_TWISTERS("Очень сложные скороговорки");
        private final String name;
        public String getName(){ return name; }
        Name(String name){
            this.name = name;
        }
    }
}
