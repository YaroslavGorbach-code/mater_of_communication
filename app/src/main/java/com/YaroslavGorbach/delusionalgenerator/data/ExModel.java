package com.YaroslavGorbach.delusionalgenerator.data;

public class ExModel {
    public int id;
    public Name name;
    public int pic;
    public String shortDesc;
    public Category category;
    public Description description;

    public ExModel(
            int id,
            Name name,
            String shortDesc,
            Category category,
            Description description,
            int pic)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.shortDesc = shortDesc;
        this.description = description;
        this.pic = pic;
    }

    public enum Category {
        SPEAKING,
        VOCABULARY,
        TONGUE_TWISTER
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
        SIMPLE_TONGUE_TWISTERS("Простые скороговорки"),
        DIFFICULT_TONGUE_TWISTERS("Сложные скороговорки"),
        VERY_DIFFICULT_TONGUE_TWISTERS("Очень сложные скороговорки");
        private final String name;
        public String getName(){ return name; }
        Name(String name){
            this.name = name;
        }
    }

    public static class Description{
        public String aim;
        public String description;
        public String example;
        public String period;

        public Description(String aim, String description, String example, String period) {
            this.aim = aim;
            this.description = description;
            this.example = example;
            this.period = period;
        }
    }
}
