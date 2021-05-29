package com.YaroslavGorbach.delusionalgenerator.feature.recorder;

import android.content.Context;

public interface VoiceRecorder {
    void start(Context context, String name);
    void stop();
    boolean getState();
}
