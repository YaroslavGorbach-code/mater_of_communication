package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.YaroslavGorbach.delusionalgenerator.data.Description_category_3;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class DescriptionCategory3FragmentViewModel extends AndroidViewModel {

    private final LiveData<Description_category_3> mDescription;

    public DescriptionCategory3FragmentViewModel(@NonNull Application application, int exId) {
        super(application);
        Repo mRepo = new Repo(application);
        mDescription = mRepo.getDescription_category_3_ByExId(exId);
    }

    public LiveData<Description_category_3> getDescription(){
        return mDescription;
    }
}
