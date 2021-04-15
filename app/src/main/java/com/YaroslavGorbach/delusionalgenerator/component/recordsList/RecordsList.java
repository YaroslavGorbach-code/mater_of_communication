package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import java.io.File;

public interface RecordsList {
    File[] getRecords(Context context);
    void onPlay(File file);
    void onStop();
    void onPause();
}
