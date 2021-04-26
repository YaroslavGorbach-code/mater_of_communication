package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Record;

import java.io.IOException;

public class MediaPlayerImp implements MediaPlayer {
    public interface Callback {
        void start(Record record);
        void finish();
    }

    private final MutableLiveData<Integer> mPlayerDuration = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> mCurrentPosition = new MutableLiveData<>(0);
    private final Handler mProgressHandler = new Handler(Looper.getMainLooper());
    private final Callback mCallback;
    private android.media.MediaPlayer mMediaPlayer;
    private Record mCurrentRecord = null;
    private Runnable progressRunnable;

    public MediaPlayerImp(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void play(Record record) {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            stop();
        }
        try {
            mMediaPlayer = new android.media.MediaPlayer();
            mMediaPlayer.setDataSource(record.getFile().getAbsolutePath());
            mMediaPlayer.prepare();
            mCallback.start(record);
            mCurrentRecord = record;
            mPlayerDuration.setValue(mMediaPlayer.getDuration());
            initProgressRunnable();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(android.media.MediaPlayer::start);
        mMediaPlayer.setOnCompletionListener(mp -> stop());
    }

    @Override
    public void stop() {
        if (mMediaPlayer!=null){
            mCallback.finish();
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mProgressHandler.removeCallbacks(progressRunnable);
        }
    }

    @Override
    public void pause() {
        if (mMediaPlayer!=null){
            mCallback.finish();
            mMediaPlayer.pause();
            mCurrentRecord.isPause = true;
            mProgressHandler.removeCallbacks(progressRunnable);
        }
    }

    @Override
    public void resume() {
        if (mMediaPlayer!=null){
            mCallback.start(mCurrentRecord);
            mMediaPlayer.start();
            mCurrentRecord.isPause = false;
            initProgressRunnable();
        }else if (mCurrentRecord!=null){
            play(mCurrentRecord);
        }
    }

    @Override
    public LiveData<Integer> getProgress() {
        return mCurrentPosition;
    }

    @Override
    public Record getRecord() {
        return mCurrentRecord;
    }

    @Override
    public LiveData<Integer> getDuration() {
        return mPlayerDuration;
    }


    @Override
    public void seekToo(int progress) {
        if (mMediaPlayer!=null){
            mMediaPlayer.seekTo(progress);
        }
    }

    private void initProgressRunnable(){
        progressRunnable = () -> {
            mCurrentPosition.setValue(mMediaPlayer.getCurrentPosition());
            mProgressHandler.postDelayed(progressRunnable, 100);
        };
        mProgressHandler.postDelayed(progressRunnable, 0);
    }

}
