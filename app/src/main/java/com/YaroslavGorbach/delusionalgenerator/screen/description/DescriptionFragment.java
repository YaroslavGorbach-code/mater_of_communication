package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.RepoImp;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.button.MaterialButton;


public class DescriptionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_description, container, false);
        // show add
        AdMob.showNativeAdd(getActivity(), view.findViewById(R.id.my_template));

        // init description
        MaterialButton startEx = view.findViewById(R.id.button_start_ex_category_1);
        TextView description = view.findViewById(R.id.description);
        DescriptionVm vm = new ViewModelProvider(this,
                new DescriptionVm.DescriptionVmFactory(new Repo.RepoProvider().provideRepo(),
                        DescriptionFragmentArgs.fromBundle(requireArguments()).getExId())).get(DescriptionVm.class);
        description.setText(vm.getDescription().description);
        startEx.setOnClickListener(v -> {
            // TODO: 4/12/2021 startEx
        });
        return view;
    }
}