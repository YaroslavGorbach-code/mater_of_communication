package com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer;

import java.io.File;

public interface MediaPlayer {
    void play(File file);
    void pause();
    void stop();
}
