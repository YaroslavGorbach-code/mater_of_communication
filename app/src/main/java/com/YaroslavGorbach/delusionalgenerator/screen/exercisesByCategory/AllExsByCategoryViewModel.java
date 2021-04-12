package com.YaroslavGorbach.delusionalgenerator.screen.exercisesByCategory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.oldDataLayer.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.oldDataLayer.RepoImpOLD;

import java.util.List;

public class AllExsByCategoryViewModel extends AndroidViewModel {
    private final RepoImpOLD mRepoImpOLD;

   public AllExsByCategoryViewModel(@NonNull Application application){
       super(application);
       mRepoImpOLD = new RepoImpOLD(application);
   }

    public LiveData<List<Exercise>> getExByCategory(int category) {
        return mRepoImpOLD.getExByCategory(category);
    }

}
