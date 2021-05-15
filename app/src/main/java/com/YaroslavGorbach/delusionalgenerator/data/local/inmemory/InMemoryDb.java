package com.YaroslavGorbach.delusionalgenerator.data.local.inmemory;

import android.content.res.Resources;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;

import java.util.List;

public interface InMemoryDb {
    List<Exercise> getExercises();
    List<Exercise> getExercises(Exercise.Category category);
    Exercise getExercise(Exercise.Name name);
    List<String> getWords(Repo.WordType type, Resources resources);
}
