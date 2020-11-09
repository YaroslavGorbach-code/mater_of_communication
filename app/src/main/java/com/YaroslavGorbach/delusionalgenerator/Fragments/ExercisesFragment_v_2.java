package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.Activities.ExerciseDescriptionActivity;
import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;

import static com.YaroslavGorbach.delusionalgenerator.Activities.ExerciseDescriptionActivity.EXTRA_ID_EX;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesFragment_v_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment_v_2 extends Fragment {


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


    public ExercisesFragment_v_2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ExercisesFragment_v_2 newInstance() {
        return new ExercisesFragment_v_2();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_version_2, container, false);
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
                startActivity(new Intent(getContext(), ExerciseDescriptionActivity.class)
                        .putExtra(EXTRA_ID_EX, id));

            });

            mRecyclerView_category_1.setHasFixedSize(true);
            mRecyclerView_category_1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_1.setAdapter(mAdapter_category_1);
        });


        mExercisesViewModel.getExByCategory(2).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_2 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;

                startActivity(new Intent(getContext(), ExerciseDescriptionActivity.class)
                .putExtra(EXTRA_ID_EX, id));

            });

            mRecyclerView_category_2.setHasFixedSize(true);
            mRecyclerView_category_2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_2.setAdapter(mAdapter_category_2);
        });


        mExercisesViewModel.getExByCategory(3).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_3 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;

                startActivity(new Intent(getContext(), ExerciseDescriptionActivity.class)
                        .putExtra(EXTRA_ID_EX, id));

            });

            mRecyclerView_category_3.setHasFixedSize(true);
            mRecyclerView_category_3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_3.setAdapter(mAdapter_category_3);
        });

        mAllExsCategory1.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                   AllExsByCategoryFragment.newInstance(1)).commit();
        });

        mAllExsCategory2.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                    AllExsByCategoryFragment.newInstance(2)).commit();
        });
        mAllExsCategory3.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                    AllExsByCategoryFragment.newInstance(3)).commit();
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}