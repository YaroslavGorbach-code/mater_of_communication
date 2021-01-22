package com.YaroslavGorbach.delusionalgenerator.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.R;

public class DescriptionCategory3FragmentViewModel extends AndroidViewModel {

    private final MutableLiveData<String> _exAim = new MutableLiveData<>();
    public LiveData<String> exAim = _exAim;

    private final MutableLiveData<String> _exDescription_1 = new MutableLiveData<>();
    public LiveData<String> exDescription_1 = _exDescription_1;

    private final MutableLiveData<String> _exDescription_2 = new MutableLiveData<>();
    public LiveData<String> exDescription_2 = _exDescription_2;

    private final MutableLiveData<String> _exDescription_3 = new MutableLiveData<>();
    public LiveData<String> exDescription_3 = _exDescription_3;

    private final MutableLiveData<String> _exDescription_4 = new MutableLiveData<>();
    public LiveData<String> exDescription_4 = _exDescription_4;

    private final MutableLiveData<String> _exDescription_5 = new MutableLiveData<>();
    public LiveData<String> exDescription_5 = _exDescription_5;

    public DescriptionCategory3FragmentViewModel(@NonNull Application application, int exId) {
        super(application);
        _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
        _exDescription_1.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32_part_1));
        _exDescription_2.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32_part_2));
        _exDescription_3.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32_part_3));
        _exDescription_4.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32_part_4));
        _exDescription_5.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32_part_5));
    }
}
