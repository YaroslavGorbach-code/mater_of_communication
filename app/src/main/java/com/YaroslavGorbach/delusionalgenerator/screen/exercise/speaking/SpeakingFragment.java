package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.component.speaking.Speaking;
import com.YaroslavGorbach.delusionalgenerator.component.speaking.SpeakingImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;
import com.YaroslavGorbach.delusionalgenerator.util.PermissionsUtil;

import javax.inject.Inject;

public class SpeakingFragment extends Fragment {
    public SpeakingFragment(){ super(R.layout.fragment_speaking); }

    public static Bundle argsOf(Exercise.Name name, Exercise.Type type){
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        bundle.putSerializable("type", type);
        return bundle;
    }

    @Inject Speaking speaking;
    @Inject AdManager adManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init vm
        Exercise.Name name = (Exercise.Name)requireArguments().getSerializable("name");
        Exercise.Type type = (Exercise.Type)requireArguments().getSerializable("type");

        SpeakingVm vm = new ViewModelProvider(this).get(SpeakingVm.class);
        vm.getSpeakingComponent(name, type, getResources()).inject(this);

        // init view
        SpeakingView v = new SpeakingView(FragmentSpeakingBinding.bind(view), new SpeakingView.Callback() {
            @Override
            public void onUp() { requireActivity().onBackPressed();}

            @Override
            public void onNext() { speaking.onNext(); }

            @Override
            public void onStartStopRecord() {
                if (PermissionsUtil.checkRecordPermission(requireActivity())){
                    speaking.onStartStopRecord(requireContext());
                }
            }

        });

        v.setTitle(getString(name.getNameId()));
        if (type == Exercise.Type.DAILY || name == Exercise.Name.REMEMBER_ALL){
            speaking.getDoneAndAim().observe(getViewLifecycleOwner(), v::setDoneAndAim);
        }
        speaking.getShortDescId().observe(getViewLifecycleOwner(), id -> v.setShortDesc(getString(id)));
        speaking.getRecordingState().observe(getViewLifecycleOwner(), v::changeButtonImage);
        speaking.getWord().observe(getViewLifecycleOwner(), v::setWord);
        adManager.loadInterstitialAd(view.getContext());

    }


    @Override
    public void onDestroy() {
        speaking.saveStatistics();
        speaking.stopRecording();
        adManager.showInterstitialAd(requireActivity());
        super.onDestroy();
    }
}


