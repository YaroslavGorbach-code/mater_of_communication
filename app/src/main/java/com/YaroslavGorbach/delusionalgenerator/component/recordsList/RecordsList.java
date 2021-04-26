package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;


public interface RecordsList {
    LiveData<List<Record>> getRecords();
    void onPlay(Record record);
    LiveData<Boolean> getIsPlaying();
    void onPause();
    void onResume();
    void onStop();
    void onSkipNext();
    void onSkipPrevious();
    void onRemove(Record record);
    LiveData<Integer> getDuration();
    LiveData<Integer> getProgress();
    void getRecordsFromFile();
    void onSeekTo(int progress);
}
