package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Statistics;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingTtBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.ChronometerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.statistics.StatisticsManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder.VoiceRecorderImp;
import com.YaroslavGorbach.delusionalgenerator.util.Permissions;

import java.util.Date;

public class SpeakingFragment extends Fragment {

    public SpeakingFragment(){ super(R.layout.fragment_speaking_tt); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentSpeakingTtBinding binding = FragmentSpeakingTtBinding.bind(view);
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());

        // init vm
        int exId = SpeakingFragmentArgs.fromBundle(requireArguments()).getIdEx();
        SpeakingVm vm = new ViewModelProvider(this, new SpeakingVm.SpeakingVmFactory(
                exId,
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
                Navigation.findNavController(view).popBackStack());

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


