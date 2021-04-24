package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;
import com.YaroslavGorbach.delusionalgenerator.util.Permissions;

public class SpeakingFragment extends Fragment {

    public SpeakingFragment(){ super(R.layout.fragment_speaking); }

    public static Bundle argsOf(Exercise.Name name){
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        return bundle;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());

        // init vm
        Exercise.Name name = (Exercise.Name)requireArguments().getSerializable("name");
        SpeakingVm vm = new ViewModelProvider(this, new SpeakingVm.SpeakingVmFactory(
                name,
                repo,
                getResources(),
                new StatisticsManagerImp(),
                new VoiceRecorderImp()
        )).get(SpeakingVm.class);

        // init view
        SpeakingView v = new SpeakingView(FragmentSpeakingBinding.bind(view), new SpeakingView.Callback() {
            @Override
            public void onUp() {((Navigation)requireActivity()).up();}

            @Override
            public void onNext() { vm.speakingEx.onNext(); }

            @Override
            public void onStartStopRecord() {
                if (Permissions.checkRecordPermission(requireActivity())){
                    vm.speakingEx.onStartStopRecord(requireContext());
                }
            }

        });

        v.setTitle(getString(name.getNameId()));
        vm.speakingEx.getShortDescId().observe(getViewLifecycleOwner(), id -> v.setShortDesc(getString(id)));
        vm.speakingEx.getRecordingState().observe(getViewLifecycleOwner(), v::changeButtonImage);
        vm.speakingEx.getWord().observe(getViewLifecycleOwner(), v::setWord);
    }
}


