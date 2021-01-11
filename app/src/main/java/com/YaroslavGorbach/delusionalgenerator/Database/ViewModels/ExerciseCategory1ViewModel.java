package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_2;
import com.YaroslavGorbach.delusionalgenerator.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExerciseCategory1ViewModel  extends AndroidViewModel {

    private final Repo_2 mRepo;
    private MediaRecorder mediaRecorder;
    private String recordFile;

    public ExerciseCategory1ViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repo_2(application);
    }

    public final MutableLiveData<Boolean> _isRecording = new MutableLiveData<>(false);
   public LiveData<Boolean> isRecording = _isRecording;

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

    public LiveData<Exercise> getExerciseById(int mIdEx) {
        return mRepo.getExerciseById(mIdEx);
    }
}
