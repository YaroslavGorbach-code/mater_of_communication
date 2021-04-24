package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import java.io.File;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RecordsList {
    Single<List<File>> getRecords(Context context);
    void onPlay(File file);
    void onStop();
    void onPause();
}
