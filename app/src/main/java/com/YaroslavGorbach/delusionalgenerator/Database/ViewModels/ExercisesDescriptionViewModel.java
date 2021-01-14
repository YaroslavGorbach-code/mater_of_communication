package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;
import com.YaroslavGorbach.delusionalgenerator.R;

public class ExercisesDescriptionViewModel extends AndroidViewModel {
    private final Repo_2 mRepo;

    private final MutableLiveData<String> _exName = new MutableLiveData<>();
    public LiveData<String> exName = _exName;

    private final MutableLiveData<String> _exAim = new MutableLiveData<>();
    public LiveData<String> exAim = _exAim;

    private final MutableLiveData<String> _exDescription = new MutableLiveData<>();
    public LiveData<String> exDescription = _exDescription;

    public ExercisesDescriptionViewModel(@NonNull Application application, int exId) {
        super(application);
        mRepo = new Repo_2(application);
        switch (exId){
            case  1:
                _exName.setValue("Лингвистические пирамиды");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_1));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_1));
                break;
            case 2:
                _exName.setValue("Чем ворон похож на стол");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_2));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_2));
                break;
            case 3:
                _exName.setValue("Чем ворон похож на стул (чувства)");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_3));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_3));
                break;
            case 4:
                _exName.setValue("Продвинутое связывание");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_4));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_4));
                break;
            case 5:
                _exName.setValue("О чем вижу, о том и пою");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_5));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_5));
                break;
            case 6:
                _exName.setValue("Другие варианты сокращений");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_6));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_6));
                break;
            case 7:
                _exName.setValue("Волшебный нейминг");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_7));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_7));
                break;
            case 8:
                _exName.setValue("Купля - продажа");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_8));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_8));
                break;
            case 9:
                _exName.setValue("Вспомнить все");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_9));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_9));
                break;
            case 10:
                _exName.setValue("В соавторстве с Далем");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_10));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_10));
                break;
            case 11:
                _exName.setValue("Тест Роршаха");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_11));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_11));
                break;
            case 12:
                _exName.setValue("Хуже уже не будет");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_12));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_12));
                break;
            case 13:
                _exName.setValue("Вопрос - ответ");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_13));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_13));
                break;
            case 14:
                _exName.setValue("Рассказчик - импровизатор");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
            case 20:
                _exName.setValue("Существительные");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_20));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_20));
                break;
            case 21:
                _exName.setValue("Прилагательные");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_21));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_21));
                break;
            case 22:
                _exName.setValue("Глаголы");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_22));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_22));
                break;
            case 30:
                _exName.setValue("Простые скороговорки");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
            case 31:
                _exName.setValue("Сложные скороговорки");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
            case 32:
                _exName.setValue("Очень сложные скороговорки");
                _exAim.setValue(application.getResources().getString(R.string.Aim_of_exercise_30_32));
                _exDescription.setValue(application.getResources().getString(R.string.Haw_to_perform_exercise_30_32));
                break;
        }
    }

    public void update(Exercise exercise){
        mRepo.update(exercise);
    }

    public LiveData<Exercise> getExerciseById(int id) {
        return mRepo.getExerciseById(id);
    }

}
