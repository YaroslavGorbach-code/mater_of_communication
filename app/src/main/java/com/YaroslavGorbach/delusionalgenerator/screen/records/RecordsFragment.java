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
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentRecordsBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.mediaPlayer.MediaPlayerImp;

import java.util.Arrays;

public class RecordsFragment extends Fragment {

    public RecordsFragment(){ super(R.layout.fragment_records); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentRecordsBinding binding = FragmentRecordsBinding.bind(view);

        //init vm
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());
        RecordsVm vm = new ViewModelProvider(this,
                new RecordsVm.RecordsVmFactory(repo, new MediaPlayerImp())).get(RecordsVm.class);

        //init records list
        RecordsListAdapter adapter = new RecordsListAdapter(file ->
                vm.recordsList.onPlay(file));
        adapter.submitList(Arrays.asList(vm.recordsList.getRecords(requireContext())));
        binding.recordsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recordsList.setAdapter(adapter);
    }
}