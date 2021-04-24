package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import com.YaroslavGorbach.delusionalgenerator.data.Record;

import java.io.File;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RecordsList {
    Single<List<Record>> getRecords(Context context);
    void onPlay(File file);
    void onStop();
    void onPause();
}
