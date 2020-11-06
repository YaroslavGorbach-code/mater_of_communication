package com.YaroslavGorbach.delusionalgenerator.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Daos.Exercise_dao;
import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;

import java.util.List;

public class Repo_2 {
    private final Exercise_dao mExDao;

    public Repo_2(Application application){
        Database database = Database.getInstance(application);
        mExDao = database.exercise_dao();
    }

    public void insertEx(Exercise exercise){ new Thread(() -> mExDao.insert(exercise)).start();
    }

    public void deleteEx(Exercise exercise){
        new Thread(() -> mExDao.delete(exercise)).start();
    }

    public void update(Exercise exercise){
        new Thread(() -> mExDao.update(exercise)).start();
    }

    public LiveData<List<Exercise>> getExByCategory(int category) {
        return mExDao.getExercisesByCategory(category);
    }

}
