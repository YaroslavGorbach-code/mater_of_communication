package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import androidx.lifecycle.LiveData;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import java.util.List;


public interface RecordsList {
    LiveData<List<Record>> getRecords();
    void onPlay(Record record);
    LiveData<Boolean> getPlayerState();
    void onPauseResume();
    void onStop();
    void onNextRecord();
    void onPrevRecord();
}
