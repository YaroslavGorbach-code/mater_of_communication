package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingTtBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.ChronometerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;
import com.YaroslavGorbach.delusionalgenerator.util.Permissions;

public class SpeakingFragment extends Fragment {

    public SpeakingFragment(){ super(R.layout.fragment_speaking_tt); }

    public static Bundle argsOf(ExModel.Name name){
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        return bundle;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentSpeakingTtBinding binding = FragmentSpeakingTtBinding.bind(view);
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());

        // init vm
        ExModel.Name name = (ExModel.Name)requireArguments().getSerializable("name");
        SpeakingVm vm = new ViewModelProvider(this, new SpeakingVm.SpeakingVmFactory(
                name,
                repo,
                getResources(),
                new ChronometerImp(binding.chronometer),
                new ChronometerImp(binding.chronometerOneWord),
                new StatisticsManagerImp(),
                new VoiceRecorderImp()
        )).get(SpeakingVm.class);

        // init short description
        vm.speakingEx.getShortDescId().observe(getViewLifecycleOwner(), descId ->
                binding.shortDesc.setText(getString(descId)));

        // init toolbar
        binding.toolbar.setNavigationOnClickListener(v ->
                ((Navigation)requireActivity()).up());
        binding.toolbar.setTitle(getString(name.getNameId()));

        // init start stop record
        binding.startStopRecord.setOnClickListener(v -> {
            if (Permissions.checkRecordPermission(requireActivity())){
                vm.speakingEx.startStopRecord(requireContext());
            }
        });

        vm.speakingEx.getRecordingState().observe(getViewLifecycleOwner(), isRecording -> {
            if (isRecording){
                binding.startStopRecord.setImageResource(R.drawable.ic_voice_recording);
            }else {
                binding.startStopRecord.setImageResource(R.drawable.ic_voice_stop);
                Toast.makeText(requireContext(), "Запись сохранена", Toast.LENGTH_SHORT).show();
            }
        });

        // init word
        vm.speakingEx.getWord().observe(getViewLifecycleOwner(), binding.word::setText);

        // init next word
        binding.next.setOnClickListener(v ->  vm.speakingEx.onNext());
    }

}


