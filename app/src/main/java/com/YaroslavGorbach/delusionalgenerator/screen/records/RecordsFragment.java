package com.YaroslavGorbach.delusionalgenerator.screen.records;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsList;
import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsListImp;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentRecordsBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class RecordsFragment extends Fragment {
    public RecordsFragment(){ super(R.layout.fragment_records); }

    @Inject RecordsList recordsList;
    @Inject AdManager adManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init vm
        RecordsVm vm = new ViewModelProvider(this).get(RecordsVm.class);
        vm.recordsComponent.inject(this);

        // init view
        RecordsView v = new RecordsView(FragmentRecordsBinding.bind(view), new RecordsView.Callback() {
            @Override
            public void onRecord(Record record) { recordsList.onPlay(record); }

            @Override
            public void onSkipNext() { recordsList.onSkipNext(); }

            @Override
            public void onSkipPrevious() { recordsList.onSkipPrevious(); }

            @Override
            public void onPause() { recordsList.onPause(); }

            @Override
            public void onResume() { recordsList.onResume(); }

            @Override
            public void onSeekTo(int progress) { recordsList.onSeekTo(progress); }

            @Override
            public void onRemove(Record record) {
                recordsList.onRemove(record);
            }
        },adManager);
        recordsList.getRecords().observe(getViewLifecycleOwner(), v::setRecords);
        recordsList.getIsPlaying().observe(getViewLifecycleOwner(), v::setIsPlaying);
        recordsList.getDuration().observe(getViewLifecycleOwner(), v::setDuration);
        recordsList.getProgress().observe(getViewLifecycleOwner(), v::setProgress);
    }

    @Override
    public void onStop() {
        super.onStop();
        recordsList.onStop();
    }
}