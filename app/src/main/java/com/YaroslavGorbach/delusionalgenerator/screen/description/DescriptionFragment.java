package com.YaroslavGorbach.delusionalgenerator.screen.description;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDescriptionBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.nav.Navigation;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class DescriptionFragment extends Fragment {
    public DescriptionFragment() {
        super(R.layout.fragment_description);
    }

    public static Bundle argsOf(Exercise.Name name, Exercise.Type type) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("name", name);
        bundle.putSerializable("type", type);
        return bundle;
    }
    private final CompositeDisposable mDisposableContainer = new CompositeDisposable();

    @Override
    public void onStart() {
        super.onStart();
        Window w = requireActivity().getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Exercise.Name name = (Exercise.Name) requireArguments().getSerializable("name");
        Exercise.Type type = (Exercise.Type) requireArguments().getSerializable("type");

        // init vm
        DescriptionVm vm = new ViewModelProvider(this,
                new DescriptionVm.DescriptionVmFactory(new Repo.RepoProvider().provideRepo(requireContext()),
                        name)).get(DescriptionVm.class);
        // init v
        DescriptionView v = new DescriptionView(FragmentDescriptionBinding.bind(view), new DescriptionView.Callback() {
            @Override
            public void onUp() { ((Navigation)requireActivity()).up(); }

            @Override
            public void onStartEx() {
                switch (vm.description.getCategory()) {
                    case SPEAKING:
                    case TONGUE_TWISTER:
                        ((Navigation) requireActivity()).openSpeakingEx(name, type);
                        break;
                    case VOCABULARY:
                        ((Navigation) requireActivity()).openVocabularyEx(name, type);
                        break;
                }
            }

            @Override
            public void onNextData() { vm.description.onStatisticsNext(); }

            @Override
            public void onPrevData() { vm.description.onStatisticsPrevious(); }

        });

        v.setTitle(getString(name.getNameId()));
        v.setImageId(vm.description.getImageId());
        v.setDescription(getString(vm.description.getDescriptionId()));
        v.setStatisticsText(vm.description.getCategory());
        vm.description.getStatistics().observe(getViewLifecycleOwner(), v::setChartData);

    }

    @Override
    public void onStop() {
            super.onStop();
        Window w = requireActivity().getWindow();
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mDisposableContainer.dispose();
    }
}