package com.YaroslavGorbach.delusionalgenerator.component.recordsList;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayer;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayerImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class RecordsListImp implements RecordsList {
   private final MediaPlayer mMediaPlayer;
   private final MutableLiveData<List<Record>> mRecords = new MutableLiveData<>();

    public RecordsListImp(Repo repo, Context context, CompositeDisposable bag){
        bag.add(repo.getRecordsFromFile(context).subscribe(mRecords::postValue));
        mMediaPlayer  = new MediaPlayerImp(new MediaPlayerImp.Callback() {
            @Override
            public void start(Record record) {
               bag.add(Observable.fromIterable(mRecords.getValue())
                       .filter(record1 -> record1.name.equals(record.name))
                       .map(record1 -> {
                           record1.isPlaying = true;
                           return record1;
                       })
                       .subscribe(playingRecord -> {
                           ArrayList<Record> list = new ArrayList<>(mRecords.getValue());
                           if(Collections.replaceAll(list, record, playingRecord))
                           mRecords.postValue(list);
                       }));
            }

            @Override
            public void finish() {
                bag.add(Observable.fromIterable(mRecords.getValue())
                        .map(record1 -> {
                            record1.isPlaying = false;
                            return record1;
                        })
                        .toList()
                        .subscribe(mRecords::postValue));
            }
        });
    }

    @Override
    public LiveData<List<Record>> getRecords() {
        return mRecords;
    }

    @Override
    public void onPlay(Record record) {
        mMediaPlayer.play(record);
    }

    @Override
    public void onStop() {
        mMediaPlayer.stop();
    }

    @Override
    public void onPause() {
        mMediaPlayer.pause();
    }
}
