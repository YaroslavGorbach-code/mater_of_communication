package com.YaroslavGorbach.delusionalgenerator.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;

import java.util.List;

public class FavoriteExsFragmentViewModel extends AndroidViewModel {
    private final Repo_2 mRepo;

    public FavoriteExsFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repo_2(application);
    }
    public LiveData<List<Exercise>> getFavoriteExs() {
        return mRepo.getFavoriteExs();
    }
}
