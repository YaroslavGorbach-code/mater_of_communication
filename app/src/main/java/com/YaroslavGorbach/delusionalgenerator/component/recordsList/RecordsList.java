package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Record;

import java.io.File;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RecordsList {
    LiveData<List<Record>> getRecords();
    void onPlay(Record record);
    void onStop();
    void onPause();
}
