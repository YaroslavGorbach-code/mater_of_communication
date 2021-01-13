package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;

import java.util.List;

public class AllExsByCategoryViewModel extends AndroidViewModel {
    private final Repo_2 mRepo;

   public AllExsByCategoryViewModel(@NonNull Application application){
       super(application);
       mRepo = new Repo_2(application);
   }

    public LiveData<List<Exercise>> getExByCategory(int category) {
        return mRepo.getExByCategory(category);
    }

}
