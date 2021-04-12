package com.YaroslavGorbach.delusionalgenerator.data;

public class ExModel {
    public String name;
    public int pic;
    public ExCategory category;
    public Description description;
    public int favorite;
    public int sort_order;
    public int id;

    public ExModel(
            int id,
            String name,
            ExCategory category,
            Description description,
            int pic, int favorite,
            int sort_order)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.pic = pic;
        this.favorite = favorite;
        this.sort_order = sort_order;
    }

    enum ExCategory{
        SPEAKING,
        VOCABULARY,
        TONGUE_TWISTER
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
