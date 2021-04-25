package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import com.YaroslavGorbach.delusionalgenerator.data.Record;

import java.io.IOException;

public class MediaPlayerImp implements MediaPlayer {
    private final Callback mCallback;
    private android.media.MediaPlayer mMediaPlayer;
    private Record mCurrentRecord = null;

    public MediaPlayerImp(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void play(Record record) {
        mCurrentRecord = record;
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            stop();
        }
        try {
            mMediaPlayer = new android.media.MediaPlayer();
            mMediaPlayer.setDataSource(record.getFile().getAbsolutePath());
            mMediaPlayer.prepare();
            mCallback.start(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(android.media.MediaPlayer::start);
        mMediaPlayer.setOnCompletionListener(mp -> mCallback.finish());
    }

    @Override
    public void pauseResume() {
        if (mCurrentRecord != null && mCurrentRecord.isPlaying) {
            mCallback.finish();
            mMediaPlayer.pause();
        } else if (mCurrentRecord != null) {
            mCallback.start(mCurrentRecord);
            mMediaPlayer.start();
        }
    }

    @Override
    public void stop() {
        mCallback.finish();
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    @Override
    public Record getCurrentRecord() {
        return mCurrentRecord;
    }

    public interface Callback {
        void start(Record record);
        void finish();
    }

}
