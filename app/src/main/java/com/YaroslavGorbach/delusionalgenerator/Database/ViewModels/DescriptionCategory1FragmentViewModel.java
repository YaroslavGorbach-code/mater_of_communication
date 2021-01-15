package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.YaroslavGorbach.delusionalgenerator.R;

public class DescriptionCategory1FragmentViewModel extends AndroidViewModel {

    private final MutableLiveData<String> _exAim = new MutableLiveData<>();
    public LiveData<String> exAim = _exAim;

    private final MutableLiveData<String> _exDescription = new MutableLiveData<>();
    public LiveData<String> exDescription = _exDescription;

    private final MutableLiveData<String> _exExample = new MutableLiveData<>();
    public LiveData<String> exExample = _exExample;

    public DescriptionCategory1FragmentViewModel(@NonNull Application application, int exId) {
        super(application);
        switch (exId){
            case  1:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_1));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_1));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_1));
                break;
            case 2:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_2));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_2));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_2));
                break;
            case 3:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_3));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_3));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_3));
                break;
            case 4:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_4));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_4));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_4));
                break;
            case 5:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_5));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_5));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_5));
                break;
            case 6:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_6));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_6));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_6));
                break;
            case 7:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_7));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_7));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_7));
                break;
            case 8:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_8));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_8));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_8));
                break;
            case 9:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_9));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_9));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_9));
                break;
            case 10:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_10));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_10));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_10));
                break;
            case 11:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_11));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_11));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_11));
                break;
            case 12:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_12));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_12));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_12));
                break;
            case 13:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_13));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_13));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_13));
                break;
            case 14:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_14));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_14));
                _exExample.setValue(application.getResources().getString(R.string.Example_exercise_14));
                break;
        }
    }
}
