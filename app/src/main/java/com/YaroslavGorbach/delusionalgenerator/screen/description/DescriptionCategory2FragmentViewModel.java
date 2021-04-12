package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Description_category_2;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class DescriptionCategory2FragmentViewModel extends AndroidViewModel {

    private Repo mRepo;
    private final LiveData<Description_category_2> mDescription;

    public DescriptionCategory2FragmentViewModel(@NonNull Application application, int exId) {
        super(application);
        mRepo = new Repo(application);
        mDescription = mRepo.getDescription_category_2_ByExId(exId);
    }

    public LiveData<Description_category_2> getDescription(){
        return mDescription;
    }
}
