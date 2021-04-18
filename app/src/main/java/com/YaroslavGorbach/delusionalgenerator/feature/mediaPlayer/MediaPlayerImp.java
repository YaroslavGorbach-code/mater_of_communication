package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MediaPlayerImp implements MediaPlayer {
   private android.media.MediaPlayer mMediaPlayer;

    @Override
    public void play(File file) {
        if (mMediaPlayer!=null && mMediaPlayer.isPlaying())
            stop();
        try {
            mMediaPlayer = new android.media.MediaPlayer();
            mMediaPlayer.setDataSource(file.getAbsolutePath());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(android.media.MediaPlayer::start);
        mMediaPlayer.setOnCompletionListener(mp -> {
            mp.release();
            mMediaPlayer = null;
        });
    }

    @Override
    public void pause() {
        mMediaPlayer.pause();
    }

    @Override
    public void stop() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}
