package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.YaroslavGorbach.delusionalgenerator.R;

public class ExercisesCategory3Fragment extends Fragment {

    private static final String ARG_EX_ID = "ARG_EX_ID";
    private int mExId;


    public ExercisesCategory3Fragment() {
        // Required empty public constructor
    }


    public static ExercisesCategory3Fragment newInstance(int exId) {
        ExercisesCategory3Fragment fragment = new ExercisesCategory3Fragment();
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
        View view = inflater.inflate(R.layout.fragment_exercises_category3, container, false);

        return view;
    }
}