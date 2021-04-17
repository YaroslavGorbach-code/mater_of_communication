package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingBinding;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.util.Permissions;

public class SpeakingFragment extends Fragment {

   public SpeakingFragment(){ super(R.layout.fragment_speaking); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentSpeakingBinding binding = FragmentSpeakingBinding.bind(view);

        // init vm
        Repo repo = new Repo.RepoProvider().provideRepo();
        int exId = SpeakingFragmentArgs.fromBundle(requireArguments()).getIdEx();
        SpeakingVm vm = new ViewModelProvider(this, new SpeakingVm.SpeakingVmFactory(
                exId, repo, getResources(), binding.chronometer, binding.chronometerOneWord)).get(SpeakingVm.class);

        // init short description
        binding.shortDesc.setText(vm.speakingEx.getShortDescId());


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
        binding.next.setOnClickListener(v ->  vm.speakingEx.nextWord());
    }

}


