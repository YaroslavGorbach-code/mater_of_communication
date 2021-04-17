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

public class RecordsFragment extends Fragment {

    public RecordsFragment(){ super(R.layout.fragment_records); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //init vn
        Repo repo = new Repo.RepoProvider().provideRepo();
        RecordsVm vm = new ViewModelProvider(this,
                new RecordsVm.RecordsVmFactory(repo)).get(RecordsVm.class);

        //init records list
        RecyclerView list = view.findViewById(R.id.records_list);
        RecordsListAdapter adapter = new RecordsListAdapter(vm.recordsList.getRecords(requireContext()),
                file -> vm.recordsList.onPlay(file));
        list.setLayoutManager(new LinearLayoutManager(requireContext()));
        list.setAdapter(adapter);
    }
}