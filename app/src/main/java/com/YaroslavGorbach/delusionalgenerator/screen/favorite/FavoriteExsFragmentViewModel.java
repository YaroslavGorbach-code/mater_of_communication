package com.YaroslavGorbach.delusionalgenerator.screen.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

import java.util.List;

public class FavoriteExsFragmentViewModel extends AndroidViewModel {
    private final Repo mRepo;

    public FavoriteExsFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repo(application);
    }
    public LiveData<List<Exercise>> getFavoriteExs() {
        return mRepo.getFavoriteExs();
    }
}
