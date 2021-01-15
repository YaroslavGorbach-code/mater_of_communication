package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.DescriptionCategory2FragmentViewModel;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.DescriptionCategory3FragmentViewModel;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.Factories.DescriptionCategory2FragmentViewModelFactory;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.Factories.DescriptionCategory3FragmentViewModelFactory;
import com.YaroslavGorbach.delusionalgenerator.R;

public class DescriptionCategory3Fragment extends Fragment {

    private static final String ARG_EX_ID = "id";
    private int mExId;
    private TextView mAimEx_tv;
    private TextView mDescriptionEx_tv;
    private DescriptionCategory3FragmentViewModel mViewModel;

    public DescriptionCategory3Fragment() {
        // Required empty public constructor
    }

    public static DescriptionCategory3Fragment newInstance(int exId) {
        DescriptionCategory3Fragment fragment = new DescriptionCategory3Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EX_ID, exId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExId = getArguments().getInt(ARG_EX_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_category_3_fragment, container, false);
        mAimEx_tv = view.findViewById(R.id.textView_aim_ex);
        //mDescriptionEx_tv = view.findViewById(R.id.textView_description_ex);
        mViewModel = new ViewModelProvider(this, new DescriptionCategory3FragmentViewModelFactory(
                getActivity().getApplication(),mExId)).get(DescriptionCategory3FragmentViewModel.class);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.exAim.observe(getViewLifecycleOwner(), aim->{
            mAimEx_tv.setText(aim);
        });
//        mViewModel.exDescription.observe(getViewLifecycleOwner(), description->{
//            mDescriptionEx_tv.setText(description);
//        });

    }
}

