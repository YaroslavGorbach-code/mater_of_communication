package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesGridListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.AllExsByCategoryViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.appbar.MaterialToolbar;


public class AllExsByCategoryFragment extends Fragment {

    private int mExCategoryId;
    private AllExsByCategoryViewModel mViewModel;
    private ExercisesGridListAdapter mAdapter;
    private RecyclerView mRecycler;


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
    getActivity().findViewById(R.id.toolbar_main_a).setVisibility(View.VISIBLE);
    mViewModel = new ViewModelProvider(this).get(AllExsByCategoryViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Создаем адаптер*/
        setGridListAdapter(view);
    }

    private void setGridListAdapter(@NonNull View view) {
        mViewModel.getExByCategory(mExCategoryId).observe(getViewLifecycleOwner(), exercises -> {
            mAdapter = new ExercisesGridListAdapter(exercises, (exercise, position) -> {
                NavDirections action = AllExsByCategoryFragmentDirections.
                        actionAllExsByCategoryFragmentToExercisesDescriptionFragment()
                        .setExId(exercise.id)
                        .setExCategory(exercise.category);
                Navigation.findNavController(view).navigate(action);
            });
            mRecycler.setHasFixedSize(true);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            mRecycler.setLayoutManager(layoutManager);
            mRecycler.setAdapter(mAdapter);
        });
    }

}