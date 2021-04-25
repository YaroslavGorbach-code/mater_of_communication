package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Record;
public interface MediaPlayer {
    void play(Record record);
    void pauseResume();
    void stop();
    LiveData<Integer> getProgress();
    Record getRecord();
    LiveData<Integer> getDuration();
    void seekToo(int progress);
}
