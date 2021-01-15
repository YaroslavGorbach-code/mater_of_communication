package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;
import com.YaroslavGorbach.delusionalgenerator.R;

public class DescriptionCategory2FragmentViewModel extends AndroidViewModel {

    private final Repo_2 mRepo;

    private final MutableLiveData<String> _exAim = new MutableLiveData<>();
    public LiveData<String> exAim = _exAim;

    private final MutableLiveData<String> _exDescription = new MutableLiveData<>();
    public LiveData<String> exDescription = _exDescription;


    public DescriptionCategory2FragmentViewModel(@NonNull Application application, int exId) {
        super(application);

        mRepo = new Repo_2(application);
        switch (exId){
            case 20:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_20));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_20));
                break;
            case 21:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_21));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_21));
                break;
            case 22:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_22));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_22));
                break;
        }
    }
}
