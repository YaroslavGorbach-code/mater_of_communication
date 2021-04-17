package com.YaroslavGorbach.delusionalgenerator.screen.records;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.recordsList.RecordsList;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;

public class RecordsFragment extends Fragment {

    public RecordsFragment(){ super(R.layout.activity_audio_list); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //init vn
        Repo repo = new Repo.RepoProvider().provideRepo();
        RecordsVm vm = new ViewModelProvider(this,
                new RecordsVm.RecordsVmFactory(repo)).get(RecordsVm.class);

        //init records list
        RecyclerView list = view.findViewById(R.id.audio_list_view);
        RecordsListAdapter adapter = new RecordsListAdapter(vm.recordsList.getRecords(requireContext()),
                file -> vm.recordsList.onPlay(file));
        list.setLayoutManager(new LinearLayoutManager(requireContext()));
        list.setAdapter(adapter);
    }
}