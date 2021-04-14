package com.YaroslavGorbach.delusionalgenerator.screen.exercise.tongue_twisters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking.SpeakingFragmentArgs;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class TongueTwisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category_3, container, false);
        /*показ банера*/
        AdMob.showBanner(view.findViewById(R.id.banner));

        // init vm
        int exId = TongueTwisterFragmentArgs.fromBundle(requireArguments()).getExId();
        Repo repo = new Repo.RepoProvider().provideRepo();
        TongueTwisterVm vm = new ViewModelProvider(this,
                new TongueTwisterVm.TongueTwisterVmFactory(exId, repo, getResources())).get(TongueTwisterVm.class);

        // init tongue twister
        TextView tt = view.findViewById(R.id.twister);
        vm.tongueTwisterEx.getTongueTwister().observe(getViewLifecycleOwner(), tt::setText);

        // init short desc
        TextView desc = view.findViewById(R.id.tongue_twister_desc);
        vm.tongueTwisterEx.getShortDesc().observe(getViewLifecycleOwner(), desc::setText);

        // init next tongue twister button
        ImageButton next = view.findViewById(R.id.nextTwist);
        next.setOnClickListener(v -> vm.tongueTwisterEx.onNextClick());

        return view;
    }
}