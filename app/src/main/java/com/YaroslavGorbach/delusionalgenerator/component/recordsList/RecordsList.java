package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import androidx.lifecycle.LiveData;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import java.util.List;


public interface RecordsList {
    LiveData<List<Record>> getRecords();
    void onPlay(Record record);
    LiveData<Boolean> getIsPlaying();
    void onPauseResume();
    void onStop();
    void onSkipNext();
    void onSkipPrevious();
    LiveData<Integer> getDuration();
    LiveData<Integer> getProgress();
    void onSeekTo(int progress);
}
