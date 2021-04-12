package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.util.Rand;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import static androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY;


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

    private ExtendedFloatingActionButton mStartRandomTrainings_bt;
    private NestedScrollView mScrollView;

    private ExercisesFragmentViewModel mViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        mRecyclerView_category_1 = view.findViewById(R.id.recyclerView_category_1);
        mRecyclerView_category_2 = view.findViewById(R.id.recyclerView_category_2);
        mRecyclerView_category_3 = view.findViewById(R.id.recyclerView_category_3);
        mAllExsCategory1 = view.findViewById(R.id.textViewAll1);
        mAllExsCategory2 = view.findViewById(R.id.textViewAll2);
        mAllExsCategory3 = view.findViewById(R.id.textViewAll3);
        mStartRandomTrainings_bt = view.findViewById(R.id.randomEx);
        mScrollView = view.findViewById(R.id.scrollView);
        mViewModel = new ViewModelProvider(this).get(ExercisesFragmentViewModel.class);
        setAdapters(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAllExsCategory1.setOnClickListener(v -> {
            NavDirections action = ExercisesFragmentDirections.
                    actionExercisesFragmentV2ToAllExsByCategoryFragment()
                    .setCategoryId(1)
                    .setCategoryName(getString(R.string.category_1_name));
            Navigation.findNavController(view).navigate(action);
        });

        mAllExsCategory2.setOnClickListener(v -> {
            NavDirections action = ExercisesFragmentDirections.
                    actionExercisesFragmentV2ToAllExsByCategoryFragment()
                    .setCategoryId(2)
                    .setCategoryName(getString(R.string.category_2_name));
            Navigation.findNavController(view).navigate(action);
        });

        mAllExsCategory3.setOnClickListener(v -> {
            NavDirections action = ExercisesFragmentDirections.
                    actionExercisesFragmentV2ToAllExsByCategoryFragment()
                    .setCategoryId(3)
                    .setCategoryName(getString(R.string.category_3_name));
            Navigation.findNavController(view).navigate(action);
        });

        mStartRandomTrainings_bt.setOnClickListener(v ->{
            int exCategory = Rand.randInt(1,10);
            int exId;
            switch (exCategory){
                case 1:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    exId = Rand.randInt(1,14);
                    exCategory = 1;
                    break;
                case 2:
                    exId = Rand.randInt(20,22);
                    break;
                case 3:
                    exId = Rand.randInt(30,32);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + exCategory);
            }

            NavDirections action = ExercisesFragmentDirections
                    .actionExercisesFragmentV2ToExercisesDescriptionFragment3()
                    .setExId(exId)
                    .setExCategory(exCategory);
            Navigation.findNavController(view).navigate(action);
        });

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    YoYo.with(Techniques.RotateOut)
                            .duration(400)
                            .playOn(mStartRandomTrainings_bt);
                } else {
                    YoYo.with(Techniques.RotateIn)
                            .duration(400)
                            .playOn(mStartRandomTrainings_bt);
                    mStartRandomTrainings_bt.show();
                }
            }
        });
    }

    private void setAdapters(View view) {
        mViewModel.getExByCategory(1).observe(getViewLifecycleOwner(), exercises -> {
            mAdapter_category_1 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = ExercisesFragmentDirections.
                        actionExercisesFragmentV2ToExercisesDescriptionFragment3()
                        .setExId(id)
                        .setExCategory(1);
                Navigation.findNavController(view).navigate(action);
            });

            mRecyclerView_category_1.setHasFixedSize(true);
            mRecyclerView_category_1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mAdapter_category_1.setStateRestorationPolicy(PREVENT_WHEN_EMPTY);
            mRecyclerView_category_1.setAdapter(mAdapter_category_1);
        });


        mViewModel.getExByCategory(2).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_2 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = ExercisesFragmentDirections.
                        actionExercisesFragmentV2ToExercisesDescriptionFragment3()
                        .setExId(id)
                        .setExCategory(2);
                Navigation.findNavController(view).navigate(action);
            });

            mRecyclerView_category_2.setHasFixedSize(true);
            mRecyclerView_category_2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mAdapter_category_2.setStateRestorationPolicy(PREVENT_WHEN_EMPTY);
            mRecyclerView_category_2.setAdapter(mAdapter_category_2);
        });


        mViewModel.getExByCategory(3).observe(getViewLifecycleOwner(), exercises -> {

            mAdapter_category_3 = new ExercisesListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = ExercisesFragmentDirections.
                        actionExercisesFragmentV2ToExercisesDescriptionFragment3()
                        .setExId(id)
                        .setExCategory(3);
                Navigation.findNavController(view).navigate(action);
            });

            mRecyclerView_category_3.setHasFixedSize(true);
            mRecyclerView_category_3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
            mAdapter_category_3.setStateRestorationPolicy(PREVENT_WHEN_EMPTY);
            mRecyclerView_category_3.setAdapter(mAdapter_category_3);
        });
    }



}
