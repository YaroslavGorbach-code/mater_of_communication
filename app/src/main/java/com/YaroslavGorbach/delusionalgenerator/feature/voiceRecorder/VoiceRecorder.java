package com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder;

import android.content.Context;

public interface VoiceRecorder {
    void start(Context context, String name);
    void stop();
    boolean getState();
}
