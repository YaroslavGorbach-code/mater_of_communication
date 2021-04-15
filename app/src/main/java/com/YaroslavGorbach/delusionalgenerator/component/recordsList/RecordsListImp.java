package com.YaroslavGorbach.delusionalgenerator.component.recordsList;

import android.content.Context;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayer;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayerImp;

import java.io.File;

public class RecordsListImp implements RecordsList {
   private final Repo mRepo;
   private final MediaPlayer mMediaPlayer;

    public RecordsListImp(Repo repo){
        mRepo = repo;
        mMediaPlayer = new MediaPlayerImp();
    }

    @Override
    public File[] getRecords(Context context) {
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
