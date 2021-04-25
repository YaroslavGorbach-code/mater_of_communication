package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import com.YaroslavGorbach.delusionalgenerator.data.Record;
public interface MediaPlayer {
    void play(Record record);
    void pauseResume();
    void stop();
    Record getCurrentRecord();
}
