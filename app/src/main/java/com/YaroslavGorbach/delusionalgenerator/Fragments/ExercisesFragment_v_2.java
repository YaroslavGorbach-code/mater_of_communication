package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.Activityes.ExercisesActivity;
import com.YaroslavGorbach.delusionalgenerator.Activityes.HelpActivity;
import com.YaroslavGorbach.delusionalgenerator.Activityes.Statistics_activity;
import com.YaroslavGorbach.delusionalgenerator.Adapters.AudioListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.R;

import java.util.ArrayList;
import java.util.List;

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


    private List<Exercise> mExercises_category_1 = new ArrayList<>();
    private List<Exercise> mExercises_category_2 = new ArrayList<>();
    private List<Exercise> mExercises_category_3 = new ArrayList<>();


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
        mExercises_category_1.add(new Exercise(1, "test1",1));
        mExercises_category_1.add(new Exercise(1, "test1",1));
        mExercises_category_1.add(new Exercise(1, "test1",1));
        mExercises_category_1.add(new Exercise(1, "test1",1));

        mRecyclerView_category_2 = view.findViewById(R.id.recyclerView_category_2);
        mExercises_category_2.add(new Exercise(1, "test2",1));
        mExercises_category_2.add(new Exercise(1, "test2",1));
        mExercises_category_2.add(new Exercise(1, "test2",1));
        mExercises_category_2.add(new Exercise(1, "test2",1));

        mRecyclerView_category_3 = view.findViewById(R.id.recyclerView_category_3);
        mExercises_category_3.add(new Exercise(1, "test3",1));
        mExercises_category_3.add(new Exercise(1, "test3",1));
        mExercises_category_3.add(new Exercise(1, "test3",1));
        mExercises_category_3.add(new Exercise(1, "test3",1));


        /*Инициализация адаптера и лисенера который отвечает за нажатие на елемент списка*/
        mAdapter_category_1 = new ExercisesListAdapter(mExercises_category_1, (exercise, position) -> {

        });
        mAdapter_category_2 = new ExercisesListAdapter(mExercises_category_2, (exercise, position) -> {

        });
        mAdapter_category_3 = new ExercisesListAdapter(mExercises_category_3, (exercise, position) -> {

        });

        /*Настройка списка*/
        mRecyclerView_category_1.setHasFixedSize(true);
        mRecyclerView_category_1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false));
        mRecyclerView_category_1.setAdapter(mAdapter_category_1);

        /*Настройка списка*/
        mRecyclerView_category_2.setHasFixedSize(true);
        mRecyclerView_category_2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false));
        mRecyclerView_category_2.setAdapter(mAdapter_category_2);

        /*Настройка списка*/
        mRecyclerView_category_3.setHasFixedSize(true);
        mRecyclerView_category_3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false));
        mRecyclerView_category_3.setAdapter(mAdapter_category_3);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}