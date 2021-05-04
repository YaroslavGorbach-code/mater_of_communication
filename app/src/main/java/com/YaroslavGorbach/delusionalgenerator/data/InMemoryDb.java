package com.YaroslavGorbach.delusionalgenerator.data;

import android.content.Context;
import android.content.res.Resources;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface InMemoryDb {
    List<Exercise> getExercises();
    List<Exercise> getExercises(Exercise.Category category);
    Exercise getExercise(Exercise.Name name);
    List<String> getWords(Repo.WordType type, Resources resources);
}
