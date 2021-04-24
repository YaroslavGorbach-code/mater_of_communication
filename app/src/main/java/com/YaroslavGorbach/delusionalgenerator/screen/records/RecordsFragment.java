package com.YaroslavGorbach.delusionalgenerator.screen.records;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentRecordsBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayerImp;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        RecordsListAdapter adapter = new RecordsListAdapter(record ->
                vm.recordsList.onPlay(record));
        vm.recordsList.getRecords().observe(getViewLifecycleOwner(), adapter::submitList);
        binding.recordsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recordsList.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        bag.dispose();
    }
}