package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.Activityes.ExerciseDescriptionActivity;
import com.YaroslavGorbach.delusionalgenerator.Activityes.Exercises2Activity;
import com.YaroslavGorbach.delusionalgenerator.Activityes.Exercises3Activity;
import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;

import static com.YaroslavGorbach.delusionalgenerator.Activityes.ExerciseDescriptionActivity.EXTRA_ID_EX;

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
        mExercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);

        mExercisesViewModel.getExByCategory(1).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_1 = new ExercisesListAdapter(exercises, (exercise, position) -> {

                // TODO: разабраться с этим id
                int id = exercise.id;
                if (id>1){
                    id++;
                }
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

                startActivity(new Intent(getContext(), Exercises2Activity.class));

            });

            mRecyclerView_category_2.setHasFixedSize(true);
            mRecyclerView_category_2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_2.setAdapter(mAdapter_category_2);
        });


        mExercisesViewModel.getExByCategory(3).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_3 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                startActivity(new Intent(getContext(), Exercises3Activity.class));

            });

            mRecyclerView_category_3.setHasFixedSize(true);
            mRecyclerView_category_3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mRecyclerView_category_3.setAdapter(mAdapter_category_3);
        });



        /*Инициализация адаптера и лисенера который отвечает за нажатие на елемент списка*/

//        mAdapter_category_2 = new ExercisesListAdapter(mExercises_category_2, (exercise, position) -> {
//
//        });
//        mAdapter_category_3 = new ExercisesListAdapter(mExercises_category_3, (exercise, position) -> {
//
//        });

        /*Настройка списка*/


//        /*Настройка списка*/
//        mRecyclerView_category_2.setHasFixedSize(true);
//        mRecyclerView_category_2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
//                false));
//        mRecyclerView_category_2.setAdapter(mAdapter_category_2);
//
//        /*Настройка списка*/
//        mRecyclerView_category_3.setHasFixedSize(true);
//        mRecyclerView_category_3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
//                false));
//        mRecyclerView_category_3.setAdapter(mAdapter_category_3);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}