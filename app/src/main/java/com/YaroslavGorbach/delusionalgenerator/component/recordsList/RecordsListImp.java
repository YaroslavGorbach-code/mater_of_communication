package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayer;

import java.io.File;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class RecordsListImp implements RecordsList {
   private final Repo mRepo;
   private final MediaPlayer mMediaPlayer;

    public RecordsListImp(Repo repo, MediaPlayer mediaPlayer){
        mRepo = repo;
        mMediaPlayer = mediaPlayer;
    }

    @Override
    public Single<List<Record>> getRecords(Context context) {
        return mRepo.getRecords(context);
    }

    @Override
    public void onPlay(File file) {
        mMediaPlayer.play(file);
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
