package com.YaroslavGorbach.delusionalgenerator.screen.records;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsList;
import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsListImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayer;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExercisesVm;

public class RecordsVm extends ViewModel {
    public RecordsList recordsList;

    public RecordsVm(Repo repo, MediaPlayer mediaPlayer){
        recordsList = new RecordsListImp(repo, mediaPlayer);
    }

    public static class RecordsVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final MediaPlayer mediaPlayer;

        public RecordsVmFactory(Repo repo, MediaPlayer mediaPlayer){
            super();
            this.repo = repo;
            this.mediaPlayer = mediaPlayer;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(RecordsVm.class)) {
                return (T)  new RecordsVm(repo, mediaPlayer);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}