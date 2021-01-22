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

import com.YaroslavGorbach.delusionalgenerator.ViewModels.DescriptionCategory3FragmentViewModel;
import com.YaroslavGorbach.delusionalgenerator.ViewModels.Factories.DescriptionCategory3FragmentViewModelFactory;
import com.YaroslavGorbach.delusionalgenerator.R;

public class DescriptionCategory3Fragment extends Fragment {

    private static final String ARG_EX_ID = "id";
    private int mExId;
    private TextView mAimEx_tv;
    private TextView mDescriptionPart_1;
    private TextView mDescriptionPart_2;
    private TextView mDescriptionPart_3;
    private TextView mDescriptionPart_4;
    private TextView mDescriptionPart_5;
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
        mDescriptionPart_1 = view.findViewById(R.id.textView_description_ex_part_1);
        mDescriptionPart_2 = view.findViewById(R.id.textView_description_ex_part_2);
        mDescriptionPart_3 = view.findViewById(R.id.textView_description_ex_part_3);
        mDescriptionPart_4 = view.findViewById(R.id.textView_description_ex_part_4);
        mDescriptionPart_5 = view.findViewById(R.id.textView_description_ex_part_5);
        mViewModel = new ViewModelProvider(this, new DescriptionCategory3FragmentViewModelFactory(
                getActivity().getApplication(),mExId)).get(DescriptionCategory3FragmentViewModel.class);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.exAim.observe(getViewLifecycleOwner(), aim-> mAimEx_tv.setText(aim));
        mViewModel.exDescription_1.observe(getViewLifecycleOwner(), part1-> mDescriptionPart_1.setText(part1));
        mViewModel.exDescription_2.observe(getViewLifecycleOwner(), part2-> mDescriptionPart_2.setText(part2));
        mViewModel.exDescription_3.observe(getViewLifecycleOwner(), part3-> mDescriptionPart_3.setText(part3));
        mViewModel.exDescription_4.observe(getViewLifecycleOwner(), part4-> mDescriptionPart_4.setText(part4));
        mViewModel.exDescription_5.observe(getViewLifecycleOwner(), part5-> mDescriptionPart_5.setText(part5));
    }
}

