package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Adapters.ExercisesGridListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.FavoriteExsFragmentViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.appbar.MaterialToolbar;

public class FavoriteExsFragment extends Fragment {


    private FavoriteExsFragmentViewModel mViewModel;
    private ExercisesGridListAdapter mAdapter;
    private RecyclerView mRecycler;
    private TextView mTextViewNoData;
    private AppCompatImageView mImageNoData;

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
        mViewModel = new ViewModelProvider(this).get(FavoriteExsFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorit_exs, container, false);
        mRecycler = view.findViewById(R.id.list_favorite_exs);
        mTextViewNoData = view.findViewById(R.id.favorite_fragment_text_nothing);
        mImageNoData = view.findViewById(R.id.favorite_fragment_image_nothing);
        setAdapter(view);
        return view;
    }

    private void setAdapter(View view) {
        mViewModel.getFavoriteExs().observe(getViewLifecycleOwner(), exercises -> {
            mAdapter = new ExercisesGridListAdapter(exercises, (exercise, position) -> {
                NavDirections action = FavoriteExsFragmentDirections.
                        actionFavoriteExsFragmentToExercisesDescriptionFragment()
                        .setExId(exercise.id)
                        .setExCategory(exercise.category);
                Navigation.findNavController(view).navigate(action);
            });

            mRecycler.setHasFixedSize(true);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            mRecycler.setLayoutManager(layoutManager);
            mRecycler.setAdapter(mAdapter);

            /*если список пустой показать картинку*/
            if (exercises != null && exercises.size() > 0){
                mImageNoData.setVisibility(View.GONE);
                mTextViewNoData.setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            }else {
                mImageNoData.setImageResource(R.drawable.ic_no_data);
                mImageNoData.setVisibility(View.VISIBLE);
                mTextViewNoData.setVisibility(View.VISIBLE);
                mRecycler.setVisibility(View.GONE);
            }
        });
    }
}