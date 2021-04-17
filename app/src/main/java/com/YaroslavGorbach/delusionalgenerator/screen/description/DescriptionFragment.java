package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.View;
import android.widget.TextView;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDescriptionBinding;
import com.google.android.material.button.MaterialButton;

public class DescriptionFragment extends Fragment {
    public DescriptionFragment(){ super(R.layout.fragment_description); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDescriptionBinding binding = FragmentDescriptionBinding.bind(view);

        DescriptionVm vm = new ViewModelProvider(this,
                new DescriptionVm.DescriptionVmFactory(new Repo.RepoProvider().provideRepo(),
                        DescriptionFragmentArgs.fromBundle(requireArguments()).getExId())).get(DescriptionVm.class);
        binding.description.setText(getString(vm.getExercise().descriptionId));
        binding.image.setImageResource(vm.getExercise().pic);
        binding.name.setText(vm.getExercise().name.getName());
        binding.startEx.setOnClickListener(v -> {
            switch (vm.getExercise().category){
                case SPEAKING:
                    Navigation.findNavController(view)
                            .navigate(DescriptionFragmentDirections
                                    .actionExercisesDescriptionFragmentToSpeakingFragment()
                                    .setIdEx(vm.getExercise().id));
                    break;
                case VOCABULARY:
                    Navigation.findNavController(view)
                            .navigate(DescriptionFragmentDirections
                                    .actionExercisesDescriptionFragmentToVocabularyFragment()
                                    .setIdEx(vm.getExercise().id));
                    break;
                case TONGUE_TWISTER:
                    Navigation.findNavController(view)
                            .navigate(DescriptionFragmentDirections
                                    .actionExercisesDescriptionFragmentToTongueTwisterFragment()
                                    .setExId(vm.getExercise().id));
            }
        });
    }
}