package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.component.speakingEx.SpeakingExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;
import com.YaroslavGorbach.delusionalgenerator.util.PermissionsUtil;

public class SpeakingFragment extends Fragment {
    public SpeakingFragment(){ super(R.layout.fragment_speaking); }

    public static Bundle argsOf(Exercise.Name name, Exercise.Type type){
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        bundle.putSerializable("type", type);
        return bundle;
    }

    private SpeakingVm vm;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init vm
        Exercise.Name name = (Exercise.Name)requireArguments().getSerializable("name");
        Exercise.Type type = (Exercise.Type)requireArguments().getSerializable("type");
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());

        vm = new ViewModelProvider(this, new SpeakingVm.SpeakingVmFactory(new SpeakingExImp(
                name,
                type,
                repo,
                new StatisticsManagerImp(),
                getResources(),
                new VoiceRecorderImp()),
                new AdManagerImp(repo))
        ).get(SpeakingVm.class);

        // init view
        SpeakingView v = new SpeakingView(FragmentSpeakingBinding.bind(view), new SpeakingView.Callback() {
            @Override
            public void onUp() { requireActivity().onBackPressed();}

            @Override
            public void onNext() { vm.speakingEx.onNext(); }

            @Override
            public void onStartStopRecord() {
                if (PermissionsUtil.checkRecordPermission(requireActivity())){
                    vm.speakingEx.onStartStopRecord(requireContext());
                }
            }

        });

        v.setTitle(getString(name.getNameId()));
        if (type == Exercise.Type.DAILY || name == Exercise.Name.REMEMBER_ALL){
            vm.speakingEx.getDoneAndAim().observe(getViewLifecycleOwner(), v::setDoneAndAim);
        }
        vm.speakingEx.getShortDescId().observe(getViewLifecycleOwner(), id -> v.setShortDesc(getString(id)));
        vm.speakingEx.getRecordingState().observe(getViewLifecycleOwner(), v::changeButtonImage);
        vm.speakingEx.getWord().observe(getViewLifecycleOwner(), v::setWord);
        vm.adManager.loadInterstitialAd(view.getContext());

    }


    @Override
    public void onDestroy() {
        vm.adManager.showInterstitialAd(requireActivity());
        super.onDestroy();
    }
}


