package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class AudioListViewModel extends AndroidViewModel {

    private MediaPlayer mediaPlayer = null;

    private final MutableLiveData<File[]> _files = new MutableLiveData<>();
    public LiveData<File[]> files = _files;

    private final MutableLiveData<Boolean> _eventPlaying = new MutableLiveData<>();
    public LiveData<Boolean> eventPlaying = _eventPlaying;

    private final MutableLiveData<Boolean> _eventPause = new MutableLiveData<>();
    public LiveData<Boolean> eventPause = _eventPause;

    public AudioListViewModel(@NonNull Application application) {
        super(application);
        getFilesFromDir(application);
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    private void getFilesFromDir(Context context) {
        /*Получаем файлы из деректории*/
        String mPath = context.getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(mPath);
        _files.setValue(directory.listFiles());

        /*Сортировка файлов по дате измененя*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (_files != null) {
                File[] sortedFiles = _files.getValue();
                Arrays.sort(sortedFiles, Comparator.comparingLong(File::lastModified).reversed());
                _files.setValue(sortedFiles);
            }
        }
    }

    public void playAudio(File fileToPlay) {
            stopAudio();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fileToPlay.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            _eventPlaying.setValue(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(mp -> {
            mediaPlayer = null;
            _eventPlaying.setValue(false);
        });
    }

    public void stopAudio() {
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer = null;
        }

    }

    public void resumeAudio() {
        if (mediaPlayer!=null){
            mediaPlayer.start();
            _eventPause.setValue(false);
        }
    }

    public void pauseAudio() {
        if (mediaPlayer!=null){
            mediaPlayer.pause();
            _eventPause.setValue(true);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        stopAudio();
    }

    public void tenSecondsAgo(int seekBarProgress) {
        seekBarProgress -= 10000;
        if (getMediaPlayer() != null) {
            getMediaPlayer().seekTo(seekBarProgress);
        }
    }

    public void tenSecondsForward(int seekBarProgress) {
        seekBarProgress += 10000;
        if (getMediaPlayer() != null) {
            getMediaPlayer().seekTo(seekBarProgress);
        }
    }
}