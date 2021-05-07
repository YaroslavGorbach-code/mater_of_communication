package com.YaroslavGorbach.delusionalgenerator.screen.records;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsList;
import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsListImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.feature.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayer;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class RecordsVm extends ViewModel {
    public RecordsList recordsList;
    public AdManager adManager;

    public RecordsVm(Repo repo, Context context, CompositeDisposable bag){
        recordsList = new RecordsListImp(repo, context, bag);
        adManager = new AdManager(repo);
    }

    public static class RecordsVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final Repo repo;
        private final Context context;
        private final CompositeDisposable bag;

        public RecordsVmFactory(Repo repo, Context context, CompositeDisposable bag){
            super();
            this.repo = repo;
            this.context = context;
            this.bag = bag;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(RecordsVm.class)) {
                return (T)  new RecordsVm(repo, context, bag);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}