package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;
import com.YaroslavGorbach.delusionalgenerator.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class ExerciseCategory1ViewModel  extends AndroidViewModel {

    private final Repo_2 mRepo_2;
    private final Repo mRepo;
    private int mIdEx;
    private MediaRecorder mediaRecorder;
    private String recordFile;
    private final Random r = new Random();


    private String [] mArrayTextUnderThumb = {};
    private String [] mArrayWorlds_ex1 = {};
    private String [] mArrayWorlds_ex2_living = {};
    private String [] mArrayWorlds_ex2_not_living = {};
    private String [] mArrayWorlds_ex3_filings = {};
    private String [] mArrayWorlds_ex4 = {};
    private String [] mArrayWorlds_ex5 = {};
    private String [] mArrayWorlds_ex6 = {};
    private String [] mArrayWorlds_ex7 = {};
    private String [] mArrayWorlds_ex8 = {};
    private String [] mArrayWorlds_ex9 = {};
    private String [] mArrayWorlds_ex10 = {};
    private String [] mArrayWorlds_ex11 = {};
    private String [] mArrayWorlds_ex12 = {};
    private String [] mArrayWorlds_ex13 = {};
    private Set<String> mSetWorlds_ex14 = new LinkedHashSet<>();

    public ExerciseCategory1ViewModel(@NonNull Application application, int exId) {
        super(application);
        mRepo_2 = new Repo_2(application);
        mRepo = Repo.getInstance(application);
        mIdEx = exId;

        switch (exId){
            case 1:
                mArrayWorlds_ex1 = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayTextUnderThumb = application.getResources().getStringArray(R.array.TextUnderThumb_ex1);
                _exName.setValue("Лингвистические пирамиды");
                _exShortDesc.setValue("Обобщайте, разобобщайте, переходите по аналогиям");
                _buttonNextText.setValue("следующее слово");
                break;
            case 2:
                mArrayWorlds_ex2_living = application.getResources().getStringArray(R.array.Worlds_items_Alive);
                mArrayWorlds_ex2_not_living = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                _exName.setValue("Чем ворон похож на стол");
                _exShortDesc.setValue("Найдите сходство");
                _buttonNextText.setValue("следующие слова");
                break;
            case 3:
                mArrayWorlds_ex2_not_living = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                mArrayWorlds_ex3_filings = application.getResources().getStringArray(R.array.Worlds_items_filings);
                _exName.setValue("Чем ворон похож на стул (чувства)");
                _exShortDesc.setValue("Найдите сходство");
                _buttonNextText.setValue("следующие слова");
                break;
            case 4:
                mArrayWorlds_ex4 = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                _exName.setValue("Продвинутое связывание");
                _exShortDesc.setValue("Найдите сходства");
                _buttonNextText.setValue("следующие слова");
                break;
            case 5:
                mArrayWorlds_ex5 = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                _exName.setValue("О чем вижу, о том и пою");
                _exShortDesc.setValue("Описывайте максимально долго данный предмет");
                _buttonNextText.setValue("следующее слово");
                break;
            case 6:
                mArrayWorlds_ex6 = application.getResources().getStringArray(R.array.Worlds_items_abbreviations);
                _exName.setValue("Другие варианты сокращений");
                _exShortDesc.setValue("Придумайте необычную расшифровку аббревиатуры");
                _buttonNextText.setValue("следующая аббревиатура");

                break;
            case 7:
                mArrayWorlds_ex7 = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                _exName.setValue("Волшебный нейминг");
                _exShortDesc.setValue("Придумайте к этому слову 5 или больше смешных прилагательных");
                _buttonNextText.setValue("следующее слово");
                break;
            case 8:
                mArrayWorlds_ex8 = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                _exName.setValue("Купля - продажа");
                _exShortDesc.setValue("Продайте это");
                _buttonNextText.setValue("следующее слово");
                break;
            case 9:
                mArrayWorlds_ex9 = application.getResources().getStringArray(R.array.letters);
                _exName.setValue("Вспомнить все");
                _exShortDesc.setValue("Назовите 15 слов на эту букву");
                _buttonNextText.setValue("следующая буква");
                break;
            case 10:
                mArrayWorlds_ex10 = application.getResources().getStringArray(R.array.Terms);
                _exName.setValue("В соавторстве с Далем");
                _exShortDesc.setValue("Дайте определение слову");
                _buttonNextText.setValue("следующее слово");
                break;
            case 11:
                mArrayWorlds_ex11 = application.getResources().getStringArray(R.array.Worlds_items_notAlive);
                _exName.setValue("Тест Роршаха");
                _exShortDesc.setValue("Придумайте чем это может быть еще");
                _buttonNextText.setValue("следующее слово");
                break;
            case 12:
                mArrayWorlds_ex12 = application.getResources().getStringArray(R.array.professions);
                _exName.setValue("Хуже уже не будет");
                _exShortDesc.setValue("Придумайте ситуацию или фразу худшего в мире");
                _buttonNextText.setValue("следующая профессия");
                break;
            case 13:
                mArrayWorlds_ex13 = application.getResources().getStringArray(R.array.questions);
                _exName.setValue("Вопрос - ответ");
                _exShortDesc.setValue("Дайте развернутый ответ на вопрос");
                _buttonNextText.setValue("следующий вопрос");
                break;
            case 14:
                mSetWorlds_ex14.addAll(Set.of((application.getResources().getStringArray(R.array.Worlds_items_Alive))));
                mSetWorlds_ex14.addAll(Set.of(application.getResources().getStringArray(R.array.Worlds_items_notAlive)));
                mSetWorlds_ex14.addAll(Set.of(application.getResources().getStringArray(R.array.professions)));
                _exName.setValue("Рассказчик - импровизатор");
                _exShortDesc.setValue("Составьте рассказ, используя данные слова");
                _buttonNextText.setValue("следующие слова");
                break;
        }

    }

    public final MutableLiveData<Boolean> _isRecording = new MutableLiveData<>(false);
    public LiveData<Boolean> isRecording = _isRecording;

    public final MutableLiveData<String> _exName = new MutableLiveData<>();
    public LiveData<String> exName = _exName;

    public final MutableLiveData<String> _exShortDesc = new MutableLiveData<>();
    public LiveData<String> exShortDesc = _exShortDesc;

    public final MutableLiveData<String> _buttonNextText = new MutableLiveData<>();
    public LiveData<String> buttonNextText = _buttonNextText;




    /*Остановка записи*/
    public void stopRecording(Context context) {
        _isRecording.setValue(false);
        Toast.makeText(context, "Запись сохранена: " + recordFile, Toast.LENGTH_SHORT).show();
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    /*Старт записи*/
    public void startRecording(String exName, Activity activity) {
        _isRecording.setValue(true);

        //Get app external directory path
        String recordPath = activity.getExternalFilesDir("/").getAbsolutePath();

        //Get current date and time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.getDefault());
        Date now = new Date();

        //initialize filename variable with date and time at the end to ensure the new file wont overwrite previous file
        recordFile = exName + " " + formatter.format(now) + ".3gp";

        //filenameText.setText("Recording, File Name : " + recordFile);

        //Setup Media Recorder for recording
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start Recording
        mediaRecorder.start();
    }

    public String getWord() {
        switch (mIdEx) {
            case 1:
                return mArrayWorlds_ex1[r.nextInt(mArrayWorlds_ex1.length)];
            case 2:
                return String.format("%s - %s", mArrayWorlds_ex2_living[r.nextInt(mArrayWorlds_ex2_living.length)],
                        mArrayWorlds_ex2_not_living[r.nextInt(mArrayWorlds_ex2_not_living.length)]);
            case 3:
               return String.format("%s - %s", mArrayWorlds_ex3_filings[r.nextInt(mArrayWorlds_ex3_filings.length)],
                       mArrayWorlds_ex2_not_living[r.nextInt(mArrayWorlds_ex2_not_living.length)]);
            case 4:
                String word_1 = mArrayWorlds_ex4[r.nextInt(mArrayWorlds_ex4.length)];
                String word_2 = mArrayWorlds_ex4[r.nextInt(mArrayWorlds_ex4.length)];
                if (word_1.equals(word_2)) {
                    word_1 = mArrayWorlds_ex4[r.nextInt(mArrayWorlds_ex4.length)];
                }
                return String.format("%s - %s", word_1, word_2);
            case 5:
                return mArrayWorlds_ex5[r.nextInt(mArrayWorlds_ex5.length)];
            case 6:
                return mArrayWorlds_ex6[r.nextInt(mArrayWorlds_ex6.length)];
            case 7:
                return mArrayWorlds_ex7[r.nextInt(mArrayWorlds_ex7.length)];
            case 8:
                return mArrayWorlds_ex8[r.nextInt(mArrayWorlds_ex8.length)];
            case 9:
               return mArrayWorlds_ex9[r.nextInt(mArrayWorlds_ex9.length)];
            case 10:
               return mArrayWorlds_ex10[r.nextInt(mArrayWorlds_ex10.length)];
            case 11:
                return mArrayWorlds_ex11[r.nextInt(mArrayWorlds_ex11.length)];
            case 12:
                return mArrayWorlds_ex12[r.nextInt(mArrayWorlds_ex12.length)];
            case 13:
                return mArrayWorlds_ex13[r.nextInt(mArrayWorlds_ex13.length)];
            case 14:
//                mSetWorlds_ex14.addAll(Set.of((getResources().getStringArray(R.array.Worlds_items_Alive))));
//                mSetWorlds_ex14.addAll(Set.of(getResources().getStringArray(R.array.Worlds_items_notAlive)));
//                mSetWorlds_ex14.addAll(Set.of(getResources().getStringArray(R.array.professions)));
//                break;
        }
        throw new IllegalArgumentException("Incorrect exercise id");
    }

    public String getThumbWord(){
        return mArrayTextUnderThumb[r.nextInt(mArrayTextUnderThumb.length)];
    }

    public LiveData<Exercise> getExerciseById(int mIdEx) {
        return mRepo_2.getExerciseById(mIdEx);
    }

    public int getMaxWorldCount(int exId){
        return  mRepo.getMaxWorldCount(exId);
    }
}
