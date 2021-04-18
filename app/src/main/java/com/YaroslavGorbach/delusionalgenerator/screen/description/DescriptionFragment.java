package com.YaroslavGorbach.delusionalgenerator.screen.description;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDescriptionBinding;

public class DescriptionFragment extends Fragment {
    public DescriptionFragment(){ super(R.layout.fragment_description); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDescriptionBinding binding = FragmentDescriptionBinding.bind(view);
        int exId =  DescriptionFragmentArgs.fromBundle(requireArguments()).getExId();

        // init vm
        DescriptionVm vm = new ViewModelProvider(this,
                new DescriptionVm.DescriptionVmFactory(new Repo.RepoProvider().provideRepo(requireContext()),
                        exId)).get(DescriptionVm.class);

        binding.description.setText(getString(vm.description.getDescriptionId()));
        binding.image.setImageResource(vm.description.getImageId());
        binding.name.setText(vm.description.getExName().getName());
        binding.startEx.setOnClickListener(v -> {
            switch (vm.description.getCategory()) {
                case SPEAKING:
                case TONGUE_TWISTER:
                    Navigation.findNavController(view)
                            .navigate(DescriptionFragmentDirections
                                    .actionExercisesDescriptionFragmentToSpeakingFragment()
                                    .setIdEx(exId));
                    break;
                case VOCABULARY:
                    Navigation.findNavController(view)
                            .navigate(DescriptionFragmentDirections
                                    .actionExercisesDescriptionFragmentToVocabularyFragment()
                                    .setIdEx(exId));
                    break;
            }
        });

        // init spinner
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(requireContext(), R.array.chart_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    binding.chart.setData(vm.description.getStatistics());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}