package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayer;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayerImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class RecordsListImp implements RecordsList {
    private final MediaPlayer mMediaPlayer;
    private final MutableLiveData<List<Record>> mRecords = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsPlaying = new MutableLiveData<>();
    private final Repo mRepo;
    private final Context mContext;
    private final CompositeDisposable mBag;

    public RecordsListImp(Repo repo, Context context, CompositeDisposable bag) {
        mRepo = repo;
        mContext = context;
        mBag = bag;
        getRecordsFromFile();
        mMediaPlayer = new MediaPlayerImp(new MediaPlayerImp.Callback() {
            @Override
            public void start(Record record) {
                mIsPlaying.postValue(true);
                bag.add(Observable.fromIterable(Objects.requireNonNull(mRecords.getValue()))
                        .filter(record1 -> record1.getName().equals(record.getName()))
                        .map(record1 -> {
                            record1.isPlaying = true;
                            return record1;
                        })
                        .subscribe(playingRecord -> {
                            ArrayList<Record> list = new ArrayList<>(mRecords.getValue());
                            if (Collections.replaceAll(list, record, playingRecord))
                                mRecords.postValue(list);
                        }));
            }

            @Override
            public void finish() {
                mIsPlaying.postValue(false);
                bag.add(Observable.fromIterable(Objects.requireNonNull(mRecords.getValue()))
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
    public LiveData<Boolean> getIsPlaying() {
        return mIsPlaying;
    }

    @Override
    public void onPause() {
        mMediaPlayer.pause();
    }

    @Override
    public void onResume() {
        mMediaPlayer.resume();
    }

    @Override
    public void onStop() {
        mMediaPlayer.stop();
    }

    @Override
    public void onSkipNext() {
        List<Record> records = Objects.requireNonNull(mRecords.getValue());
        for (int i = 0; i < records.size() - 1; i++) {
            if (mMediaPlayer.getRecord() != null
                    && records.get(i).getName().equals(mMediaPlayer.getRecord().getName())
                    && i + 1 != records.size()) {
                mMediaPlayer.play(records.get(i + 1));
                break;
            }
        }

    }

    @Override
    public void onSkipPrevious() {
        List<Record> records = Objects.requireNonNull(mRecords.getValue());
        for (int i = 0; i < records.size(); i++) {
            if (mMediaPlayer.getRecord() != null
                    && records.get(i).getName().equals(mMediaPlayer.getRecord().getName())
                    && i - 1 != -1) {
                mMediaPlayer.play(records.get(i - 1));
                break;
            }
        }
    }

    @Override
    public void onRemove(Record record) {
        if (record.isPlaying)
            mMediaPlayer.stop();
        mRepo.deleteRecord(record);
    }

    @Override
    public LiveData<Integer> getDuration() {
        return mMediaPlayer.getDuration();
    }

    @Override
    public LiveData<Integer> getProgress() {
        return mMediaPlayer.getProgress();
    }

    @Override
    public void getRecordsFromFile() {
        mBag.add(mRepo.getRecordsFromFile(mContext)
                .subscribe(mRecords::postValue));
    }

    @Override
    public void onSeekTo(int progress) {
        mMediaPlayer.seekToo(progress);
    }
}
