package com.YaroslavGorbach.delusionalgenerator.screen.exercise.tonguetwisters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentTongueTwistersBinding;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;

public class TongueTwisterFragment extends Fragment {

    public TongueTwisterFragment(){ super(R.layout.fragment_tongue_twisters); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTongueTwistersBinding binding = FragmentTongueTwistersBinding.bind(view);
        //show add
        AdMob.showBanner(binding.banner);

        // init vm
        int exId = TongueTwisterFragmentArgs.fromBundle(requireArguments()).getExId();
        Repo repo = new Repo.RepoProvider().provideRepo();
        TongueTwisterVm vm = new ViewModelProvider(this,
                new TongueTwisterVm.TongueTwisterVmFactory(exId, repo, getResources())).get(TongueTwisterVm.class);

        // init tongue twister
        vm.tongueTwisterEx.getTongueTwister().observe(getViewLifecycleOwner(), binding.twister::setText);

        // init short desc
        vm.tongueTwisterEx.getShortDescId().observe(getViewLifecycleOwner(), id ->
                binding.shortDesc.setText(getString(id)));

        // init next tongue twister button
        binding.next.setOnClickListener(v -> vm.tongueTwisterEx.onNextClick());
    }
}