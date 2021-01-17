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
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.Factories.DescriptionCategory2FragmentViewModelFactory;
import com.YaroslavGorbach.delusionalgenerator.R;

    public class DescriptionCategory2Fragment extends Fragment {

        private static final String ARG_EX_ID = "id";
        private int mExId;
        private TextView mAimEx_tv;
        private TextView mDescriptionEx_tv;
        private DescriptionCategory2FragmentViewModel mViewModel;
        private TextView mPeriodEx_tv;

        public DescriptionCategory2Fragment() {
            // Required empty public constructor
        }

        public static DescriptionCategory2Fragment newInstance(int exId) {
            DescriptionCategory2Fragment fragment = new DescriptionCategory2Fragment();
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
            View view = inflater.inflate(R.layout.description_category_2_fragment, container, false);
            mAimEx_tv = view.findViewById(R.id.textView_aim_ex);
            mDescriptionEx_tv = view.findViewById(R.id.textView_description_ex);
            mPeriodEx_tv = view.findViewById(R.id.textView_period_ex);
            mViewModel = new ViewModelProvider(this, new DescriptionCategory2FragmentViewModelFactory(
                    getActivity().getApplication(),mExId)).get(DescriptionCategory2FragmentViewModel.class);

            return view;

        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mViewModel.exAim.observe(getViewLifecycleOwner(), aim-> mAimEx_tv.setText(aim));
            mViewModel.exDescription.observe(getViewLifecycleOwner(), description-> mDescriptionEx_tv.setText(description));
            mViewModel.exPeriod.observe(getViewLifecycleOwner(), period ->mPeriodEx_tv.setText(period));
        }
    }

