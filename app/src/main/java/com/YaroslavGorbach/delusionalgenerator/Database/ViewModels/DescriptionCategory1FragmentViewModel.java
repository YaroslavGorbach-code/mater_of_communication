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
                break;
            case 2:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_2));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_2));
                break;
            case 3:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_3));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_3));
                break;
            case 4:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_4));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_4));
                break;
            case 5:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_5));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_5));
                break;
            case 6:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_6));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_6));
                break;
            case 7:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_7));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_7));
                break;
            case 8:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_8));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_8));
                break;
            case 9:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_9));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_9));
                break;
            case 10:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_10));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_10));
                break;
            case 11:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_11));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_11));
                break;
            case 12:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_12));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_12));
                break;
            case 13:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_13));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_13));
                break;
            case 14:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
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
            case 30:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
            case 31:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
            case 32:
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
        }
    }
}
