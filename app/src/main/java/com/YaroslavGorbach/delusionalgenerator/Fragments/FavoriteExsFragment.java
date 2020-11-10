package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.YaroslavGorbach.delusionalgenerator.Activities.ExerciseDescriptionActivity;
import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesGridListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Utility;

import static com.YaroslavGorbach.delusionalgenerator.Activities.ExerciseDescriptionActivity.EXTRA_ID_EX;

public class FavoriteExsFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private ExercisesViewModel mExercisesViewModel;
    private ExercisesGridListAdapter mAdapter;
    private RecyclerView mRecycler;


    public FavoriteExsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FavoriteExsFragment newInstance(){
        FavoriteExsFragment fragment = new FavoriteExsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorit_exs, container, false);
        mRecycler = view.findViewById(R.id.list_favorite_exs);

        mExercisesViewModel.getFavoriteExs().observe(getViewLifecycleOwner(), exercises -> {
            mAdapter = new ExercisesGridListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                startActivity(new Intent(getContext(), ExerciseDescriptionActivity.class)
                        .putExtra(EXTRA_ID_EX, id));
            });
            int mNoOfColumns = Utility.calculateNoOfColumns(getContext(), 190);
            mRecycler.setHasFixedSize(true);
            mRecycler.setLayoutManager(new StaggeredGridLayoutManager( mNoOfColumns, StaggeredGridLayoutManager.VERTICAL));
            mRecycler.setAdapter(mAdapter);
        });


        return view;
    }
}