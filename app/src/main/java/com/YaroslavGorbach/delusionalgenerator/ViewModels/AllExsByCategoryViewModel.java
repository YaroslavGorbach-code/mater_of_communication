package com.YaroslavGorbach.delusionalgenerator.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;

import java.util.List;

public class AllExsByCategoryViewModel extends AndroidViewModel {
    private final Repo mRepo;

   public AllExsByCategoryViewModel(@NonNull Application application){
       super(application);
       mRepo = new Repo(application);
   }

    public LiveData<List<Exercise>> getExByCategory(int category) {
        return mRepo.getExByCategory(category);
    }

}
