package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Activities.MainActivity;
import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesGridListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Utility;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.appbar.MaterialToolbar;

public class FavoriteExsFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private ExercisesViewModel mExercisesViewModel;
    private ExercisesGridListAdapter mAdapter;
    private RecyclerView mRecycler;
    private TextView mTextViewNoData;
    private AppCompatImageView mImageNoData;



    public FavoriteExsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FavoriteExsFragment newInstance(){
        FavoriteExsFragment fragment = new FavoriteExsFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.bttm_nav).setVisibility(View.VISIBLE);
        MaterialToolbar toolbar = getActivity().findViewById(R.id.toolbar_main_a);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.getMenu().getItem(0).setVisible(false);

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

        mTextViewNoData = view.findViewById(R.id.favorite_fragment_text_nothing);
        mImageNoData = view.findViewById(R.id.favorite_fragment_image_nothing);

        mExercisesViewModel.getFavoriteExs().observe(getViewLifecycleOwner(), exercises -> {
            mAdapter = new ExercisesGridListAdapter(exercises, (exercise, position) -> {
                int id = exercise.id;
                NavDirections action = FavoriteExsFragmentDirections.
                        actionFavoriteExsFragmentToExercisesDescriptionFragment().setExId(id);
                Navigation.findNavController(view).navigate(action);
            });
            //int mNoOfColumns = Utility.calculateNoOfColumns(getContext(), 190);
            mRecycler.setHasFixedSize(true);
            //mRecycler.setLayoutManager(new StaggeredGridLayoutManager( mNoOfColumns, StaggeredGridLayoutManager.VERTICAL));

            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
            layoutManager.setJustifyContent(JustifyContent.SPACE_EVENLY);
            mRecycler.setLayoutManager(layoutManager);

            mRecycler.setAdapter(mAdapter);

            if (exercises != null && exercises.size() > 0){
                mImageNoData.setVisibility(View.GONE);
                mTextViewNoData.setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            }else {
                String color = Repo.getInstance(getContext()).getThemeState();
                switch (color){
                    case "blue":
                        mImageNoData.setImageResource(R.drawable.no_data_blue);
                        break;

                    case "green":
                        mImageNoData.setImageResource(R.drawable.no_data_green);
                        break;

                    case "orange":
                        mImageNoData.setImageResource(R.drawable.no_files);
                        break;

                    case "red":
                        mImageNoData.setImageResource(R.drawable.no_data_red);
                        break;

                    case "purple":
                        mImageNoData.setImageResource(R.drawable.no_data_purpure);
                        break;

                }
                mImageNoData.setVisibility(View.VISIBLE);
                mTextViewNoData.setVisibility(View.VISIBLE);
                mRecycler.setVisibility(View.GONE);
            }

        });


        return view;
    }
}