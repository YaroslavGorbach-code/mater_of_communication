package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesGridListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Utility;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;


public class AllExsByCategoryFragment extends Fragment {

    private int mExCategoryId;
    private ExercisesViewModel mExercisesViewModel;
    private ExercisesGridListAdapter mAdapter;
    private RecyclerView mRecycler;
    private MaterialToolbar mMaterialToolbar;
    private CardView mCardView;


    public AllExsByCategoryFragment() {
        // Required empty public constructor
    }


    public static AllExsByCategoryFragment newInstance(){
        return new AllExsByCategoryFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.bttm_nav).setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_all_exs_by_category, container, false);
    mRecycler = view.findViewById(R.id.list_exs_by_category);
    mExCategoryId = AllExsByCategoryFragmentArgs.fromBundle(getArguments()).getCategoryId();
    mExercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
    mMaterialToolbar = getActivity().findViewById(R.id.toolbar_main_a);
    mMaterialToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
    mCardView = view.findViewById(R.id.gridItem);
    getActivity().findViewById(R.id.toolbar_main_a).setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMaterialToolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        /*Создаем адаптер*/
        mExercisesViewModel.getExByCategory(mExCategoryId).observe(getViewLifecycleOwner(), exercises -> {
            mAdapter = new ExercisesGridListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = AllExsByCategoryFragmentDirections.
                        actionAllExsByCategoryFragmentToExercisesDescriptionFragment().setExId(id);
                Navigation.findNavController(view).navigate(action);
            });



            //int mNoOfColumns = Utility.calculateNoOfColumns(getContext(), 180);
            mRecycler.setHasFixedSize(true);
            //mRecycler.setLayoutManager(new StaggeredGridLayoutManager( mNoOfColumns, StaggeredGridLayoutManager.VERTICAL));

            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
            layoutManager.setJustifyContent(JustifyContent.SPACE_EVENLY);
            mRecycler.setLayoutManager(layoutManager);

            mRecycler.setAdapter(mAdapter);

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMaterialToolbar.setNavigationIcon(null);
    }
}