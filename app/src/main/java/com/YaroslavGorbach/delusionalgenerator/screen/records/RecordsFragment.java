package com.YaroslavGorbach.delusionalgenerator.screen.records;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentRecordsBinding;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class RecordsFragment extends Fragment {
    public RecordsFragment(){ super(R.layout.fragment_records); }
    private final CompositeDisposable bag = new CompositeDisposable();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentRecordsBinding binding = FragmentRecordsBinding.bind(view);

        //init vm
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());
        RecordsVm vm = new ViewModelProvider(this,
                new RecordsVm.RecordsVmFactory(repo, requireContext(), bag)).get(RecordsVm.class);

        //init records list
        RecordsAdapter adapter = new RecordsAdapter(record ->{
            vm.recordsList.onPlay(record);
            binding.include.playerRecordName.setText(record.getName());
        });
        vm.recordsList.getRecords().observe(getViewLifecycleOwner(), adapter::setData);
        binding.recordsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recordsList.setAdapter(adapter);

        // init player
        vm.recordsList.getPlayerState().observe(getViewLifecycleOwner(), isPlaying -> {
            if (isPlaying){
                binding.include.playerStartPause.setImageResource(R.drawable.ic_pause_round);
            }else {
                binding.include.playerStartPause.setImageResource(R.drawable.ic_play_round);
            }
        });

        binding.include.playerSkipNext.setOnClickListener(v -> {
            vm.recordsList.onNextRecord();
        });

        binding.include.playerSkipPrevious.setOnClickListener(v->{
            vm.recordsList.onPrevRecord();
        });

        binding.include.playerStartPause.setOnClickListener(v -> {
            vm.recordsList.onPauseResume();
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        bag.dispose();
    }
}