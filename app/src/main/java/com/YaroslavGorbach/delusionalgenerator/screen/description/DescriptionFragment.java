package com.YaroslavGorbach.delusionalgenerator.screen.description;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.YaroslavGorbach.delusionalgenerator.component.description.Description;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDescriptionBinding;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class DescriptionFragment extends Fragment {

    public interface Router{
        void openSpeaking(Exercise.Name name, Exercise.Type type);
        void openVocabulary(Exercise.Name name, Exercise.Type type);
    }

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

    @Inject Description description;

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

        // init vm
        Exercise.Name name = (Exercise.Name) requireArguments().getSerializable("name");
        Exercise.Type type = (Exercise.Type) requireArguments().getSerializable("type");

        DescriptionVm vm = new ViewModelProvider(this).get(DescriptionVm.class);
        vm.getDescriptionComponent(name).inject(this);

        // init v
        DescriptionView v = new DescriptionView(FragmentDescriptionBinding.bind(view), new DescriptionView.Callback() {
            @Override
            public void onUp() { requireActivity().onBackPressed(); }

            @Override
            public void onStartEx() {
                switch (description.getCategory()) {
                    case SPEAKING:
                    case TONGUE_TWISTER:
                        ((Router) requireParentFragment()).openSpeaking(name, type);
                        break;
                    case VOCABULARY:
                        ((Router) requireParentFragment()).openVocabulary(name, type);
                        break;
                }
            }

            @Override
            public void onNextData() { description.onChartNext(); }

            @Override
            public void onPrevData() { description.onChartBack(); }

        });

        v.setTitle(getString(name.getNameId()));
        v.setImageId(description.getImageId());
        v.setDescription(getString(description.getDescriptionId()));
        v.setStatisticsText(description.getCategory());
        description.getChartData().observe(getViewLifecycleOwner(), v::setChartData);
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