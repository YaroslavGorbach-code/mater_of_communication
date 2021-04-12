package com.YaroslavGorbach.delusionalgenerator.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepoImpOLD {
    private final Exercise_dao mExDao;

    public RepoImpOLD(Application application){
        Database database = Database.getInstance(application);
        mExDao = database.exercise_dao();
    }

    public void update(Exercise exercise){
        new Thread(() -> mExDao.update(exercise)).start();
    }

    public LiveData<List<Exercise>> getExByCategory(int category) {
        return mExDao.getExercisesByCategory(category);
    }

    public LiveData<Exercise> getExerciseById(int id) {
        return mExDao.getExerciseById(id);
    }

}
