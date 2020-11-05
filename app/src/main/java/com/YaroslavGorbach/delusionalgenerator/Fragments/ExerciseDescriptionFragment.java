package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseDescriptionFragment extends Fragment {


    private static final String ARG_ID_EX = "ARG_ID_EX";


    private int mExId;
    private MaterialButton mStartExButton;

    public ExerciseDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param exId Parameter 1.
     * @return A new instance of fragment ExerciseDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseDescriptionFragment newInstance(int exId) {
        ExerciseDescriptionFragment fragment = new ExerciseDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_EX, exId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExId = getArguments().getInt(ARG_ID_EX, -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_description, container, false);
        mStartExButton = view.findViewById(R.id.button_start_ex_category_1);

        mStartExButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.exercise_description_container,
                    ExercisesCategory1Fragment.newInstance(mExId)).commit();
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}