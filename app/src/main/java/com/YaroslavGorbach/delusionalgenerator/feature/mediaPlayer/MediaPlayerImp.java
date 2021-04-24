package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import com.YaroslavGorbach.delusionalgenerator.data.Record;

import java.io.IOException;

public class MediaPlayerImp implements MediaPlayer {
   private android.media.MediaPlayer mMediaPlayer;
   private final Callback mCallback;
   public interface Callback{
       void start(Record record);
       void finish();
   }

   public MediaPlayerImp(Callback callback){
       mCallback = callback;
   }

    @Override
    public void play(Record record) {

        if (mMediaPlayer!=null && mMediaPlayer.isPlaying()){
            stop();
        }

        try {
            mMediaPlayer = new android.media.MediaPlayer();
            mMediaPlayer.setDataSource(record.getFile().getAbsolutePath());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(android.media.MediaPlayer::start);
        mCallback.start(record);
        mMediaPlayer.setOnCompletionListener(mp -> mCallback.finish());
    }

    @Override
    public void pause() {
        mMediaPlayer.pause();
    }

    @Override
    public void stop() {
        mCallback.finish();
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}
