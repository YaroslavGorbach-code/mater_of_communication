package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category_1, container, false);
        // show add
        AdMob.showBanner(view.findViewById(R.id.banner));

        // init speaking exercise
        Repo repo = new Repo.RepoProvider().provideRepo();
        int exId = SpeakingFragmentArgs.fromBundle(requireArguments()).getIdEx();
        Chronometer chronometer = view.findViewById(R.id.chronometer);
        Chronometer chronometerOneWord = view.findViewById(R.id.chronometer_one_word);
        SpeakingVm vm = new ViewModelProvider(this, new SpeakingVm.SpeakingVmFactory(
                exId, repo, getResources(), chronometer, chronometerOneWord,
                requireContext().getExternalFilesDir("/").getAbsolutePath())).get(SpeakingVm.class);

        TextView shortDesc = view.findViewById(R.id.description_short);
        shortDesc.setText(vm.speakingEx.getShortDesc());

        ImageButton startPause = view.findViewById(R.id.button);
        startPause.setOnClickListener(v -> vm.speakingEx.startPauseChronometer());

        Button startStopRecord = view.findViewById(R.id.start_stop_record);
        startStopRecord.setOnClickListener(v -> {
            if (Permissions.checkRecordPermission(requireActivity())){
                vm.speakingEx.startStopRecord();
            }
        });
        vm.speakingEx.getRecordingState().observe(getViewLifecycleOwner(), isRecording -> {
            if (!isRecording)
                Toast.makeText(requireContext(), "Запись сохранена", Toast.LENGTH_SHORT).show();
        });

        TextView  mWorld = view.findViewById(R.id.world_tv);
        vm.speakingEx.getWord().observe(getViewLifecycleOwner(), mWorld::setText);

        Button nextWord = view.findViewById(R.id.buttonNextWorld);
        nextWord.setOnClickListener(v ->  vm.speakingEx.nextWord());

        return view;
    }
}


