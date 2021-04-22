package com.YaroslavGorbach.delusionalgenerator.screen.description;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDescriptionBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

import java.util.List;

public class DescriptionFragment extends Fragment {
    public DescriptionFragment(){ super(R.layout.fragment_description); }

    public static Bundle argsOf(ExModel.Name name){
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        return bundle;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDescriptionBinding binding = FragmentDescriptionBinding.bind(view);
        ExModel.Name name = (ExModel.Name) requireArguments().getSerializable("name");

        // init vm
        DescriptionVm vm = new ViewModelProvider(this,
                new DescriptionVm.DescriptionVmFactory(new Repo.RepoProvider().provideRepo(requireContext()),
                        name)).get(DescriptionVm.class);

        binding.description.setText(getString(vm.description.getDescriptionId()));
        binding.image.setImageResource(vm.description.getImageId());
        binding.name.setText(getString(vm.description.getExName().getNameId()));
        binding.startEx.setOnClickListener(v -> {
            switch (vm.description.getCategory()) {
                case SPEAKING:
                case TONGUE_TWISTER:
                    ((Navigation) requireActivity()).openSpeakingEx(name);
                    break;
                case VOCABULARY:
                    ((Navigation) requireActivity()).openVocabularyEx(name);
                    break;
            }
        });

        // init statistics
        if (vm.description.getCategory() == ExModel.Category.TONGUE_TWISTER)
            binding.statisticsText.setText(R.string.statistics_text_tt);

        binding.chart.setData(vm.description.getStatisticsLast());
        binding.nextData.setOnClickListener(v -> {
            List<InputData> data = vm.description.getStatisticsNext();
            if (data.size()>0)
           binding.chart.setData(data);
        });

        binding.prevData.setOnClickListener(v -> {
            List<InputData> data = vm.description.getStatisticsPrevious();
            if (data.size()>0)
            binding.chart.setData(data);
        });
    }
}