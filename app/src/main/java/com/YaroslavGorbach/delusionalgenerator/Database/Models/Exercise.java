package com.YaroslavGorbach.delusionalgenerator.Database.Models;

public class Exercise {
    public long id;
    public String name;
    public int category;

    public Exercise(long id, String name, int category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
