package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.domain.Record;
public interface MediaPlayer {
    void play(Record record);
    void stop();
    void pause();
    void resume();
    LiveData<Integer> getProgress();
    Record getRecord();
    LiveData<Integer> getDuration();
    void seekToo(int progress);
}
