package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.YaroslavGorbach.delusionalgenerator.Activities.ExerciseDescriptionActivity;
import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Utility;

import static com.YaroslavGorbach.delusionalgenerator.Activities.ExerciseDescriptionActivity.EXTRA_ID_EX;


public class AllExsByCategoryFragment extends Fragment {


    private static final String ARG_EX_CATEGORY_ID = "ARG_EX_CATEGORY_ID";

    // TODO: Rename and change types of parameters
    private int mExCategoryId;
    private ExercisesViewModel mExercisesViewModel;
    private ExercisesListAdapter mAdapter;
    private RecyclerView mRecycler;


    public AllExsByCategoryFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AllExsByCategoryFragment newInstance(int mExCategoryId){
        AllExsByCategoryFragment fragment = new AllExsByCategoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EX_CATEGORY_ID, mExCategoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExCategoryId = getArguments().getInt(ARG_EX_CATEGORY_ID);
        }
        mExercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_all_exs_by_category, container, false);
    mRecycler = view.findViewById(R.id.list_exs_by_category);

        mExercisesViewModel.getExByCategory(mExCategoryId).observe(getViewLifecycleOwner(), exercises -> {
            mAdapter = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                startActivity(new Intent(getContext(), ExerciseDescriptionActivity.class)
                        .putExtra(EXTRA_ID_EX, id));
            });
           int mNoOfColumns = Utility.calculateNoOfColumns(getContext(), 200);
            mRecycler.setHasFixedSize(true);
            mRecycler.setLayoutManager(new GridLayoutManager(getContext(), mNoOfColumns));
            mRecycler.setAdapter(mAdapter);
        });


        return view;
    }
}