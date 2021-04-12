package com.YaroslavGorbach.delusionalgenerator.screen.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.RepoImpOLD;

import java.util.List;

public class FavoriteExsFragmentViewModel extends AndroidViewModel {
    private final RepoImpOLD mRepoImpOLD;

    public FavoriteExsFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepoImpOLD = new RepoImpOLD(application);
    }
    public LiveData<List<Exercise>> getFavoriteExs() {
        return mRepoImpOLD.getFavoriteExs();
    }
}
