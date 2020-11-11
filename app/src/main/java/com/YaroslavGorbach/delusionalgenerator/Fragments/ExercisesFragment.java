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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment {


    private RecyclerView mRecyclerView_category_1;
    private RecyclerView mRecyclerView_category_2;
    private RecyclerView mRecyclerView_category_3;

    private ExercisesListAdapter mAdapter_category_1;
    private ExercisesListAdapter mAdapter_category_2;
    private ExercisesListAdapter mAdapter_category_3;

    private TextView mAllExsCategory1;
    private TextView mAllExsCategory2;
    private TextView mAllExsCategory3;

    private ExercisesViewModel mExercisesViewModel;


    public ExercisesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ExercisesFragment newInstance() {
        return new ExercisesFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.bttm_nav).setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        mRecyclerView_category_1 = view.findViewById(R.id.recyclerView_category_1);
        mRecyclerView_category_2 = view.findViewById(R.id.recyclerView_category_2);
        mRecyclerView_category_3 = view.findViewById(R.id.recyclerView_category_3);

        mAllExsCategory1 = view.findViewById(R.id.textViewAll1);
        mAllExsCategory2 = view.findViewById(R.id.textViewAll2);
        mAllExsCategory3 = view.findViewById(R.id.textViewAll3);


        mExercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);

        mExercisesViewModel.getExByCategory(1).observe(getViewLifecycleOwner(), exercises -> {
            mAdapter_category_1 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = ExercisesFragmentDirections.
                        actionExercisesFragmentV2ToExercisesDescriptionFragment3().setExId(id);
                Navigation.findNavController(view).navigate(action);
            });

            mRecyclerView_category_1.setHasFixedSize(true);
            mRecyclerView_category_1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_1.setAdapter(mAdapter_category_1);
        });


        mExercisesViewModel.getExByCategory(2).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_2 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = ExercisesFragmentDirections.
                        actionExercisesFragmentV2ToExercisesDescriptionFragment3().setExId(id);
                Navigation.findNavController(view).navigate(action);
            });

            mRecyclerView_category_2.setHasFixedSize(true);
            mRecyclerView_category_2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_2.setAdapter(mAdapter_category_2);
        });


        mExercisesViewModel.getExByCategory(3).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_3 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = ExercisesFragmentDirections.
                        actionExercisesFragmentV2ToExercisesDescriptionFragment3().setExId(id);
                Navigation.findNavController(view).navigate(action);
            });

            mRecyclerView_category_3.setHasFixedSize(true);
            mRecyclerView_category_3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_3.setAdapter(mAdapter_category_3);
        });



            return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAllExsCategory1.setOnClickListener(v -> {
            NavDirections action = ExercisesFragmentDirections.
                    actionExercisesFragmentV2ToAllExsByCategoryFragment().setCategoryId(1);
            Navigation.findNavController(view).navigate(action);
        });

        mAllExsCategory2.setOnClickListener(v -> {
            NavDirections action = ExercisesFragmentDirections.
                    actionExercisesFragmentV2ToAllExsByCategoryFragment().setCategoryId(2);
            Navigation.findNavController(view).navigate(action);
        });

        mAllExsCategory3.setOnClickListener(v -> {
            NavDirections action = ExercisesFragmentDirections.
                    actionExercisesFragmentV2ToAllExsByCategoryFragment().setCategoryId(3);
            Navigation.findNavController(view).navigate(action);
        });
    }

}