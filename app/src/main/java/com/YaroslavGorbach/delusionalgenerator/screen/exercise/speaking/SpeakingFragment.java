package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.util.Permissions;

public class SpeakingFragment extends Fragment {

   public SpeakingFragment(){ super(R.layout.fragment_speaking); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdMob.showBanner(view.findViewById(R.id.banner));

        // init vm
        Repo repo = new Repo.RepoProvider().provideRepo();
        int exId = SpeakingFragmentArgs.fromBundle(requireArguments()).getIdEx();
        Chronometer chronometer = view.findViewById(R.id.chronometer);
        Chronometer chronometerOneWord = view.findViewById(R.id.chronometer_one_word);
        SpeakingVm vm = new ViewModelProvider(this, new SpeakingVm.SpeakingVmFactory(
                exId, repo, getResources(), chronometer, chronometerOneWord)).get(SpeakingVm.class);

        // init short description
        TextView shortDesc = view.findViewById(R.id.short_desc);
        shortDesc.setText(vm.speakingEx.getShortDesc());

        // init chronometer
        ImageButton startPause = view.findViewById(R.id.pouse_resume);
        startPause.setOnClickListener(v -> vm.speakingEx.startPauseChronometer());

        // init start stop record
        Button startStopRecord = view.findViewById(R.id.start_stop_record);
        startStopRecord.setOnClickListener(v -> {
            if (Permissions.checkRecordPermission(requireActivity())){
                vm.speakingEx.startStopRecord(requireContext());
            }
        });
        vm.speakingEx.getRecordingState().observe(getViewLifecycleOwner(), isRecording -> {
            if (!isRecording)
                Toast.makeText(requireContext(), "Запись сохранена", Toast.LENGTH_SHORT).show();
        });

        // init word
        TextView  mWord = view.findViewById(R.id.word);
        vm.speakingEx.getWord().observe(getViewLifecycleOwner(), mWord::setText);

        // init next word
        Button nextWord = view.findViewById(R.id.next);
        nextWord.setOnClickListener(v ->  vm.speakingEx.nextWord());
    }

}


