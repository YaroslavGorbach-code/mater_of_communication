package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.ViewModels.ExercisesDescriptionViewModel;
import com.YaroslavGorbach.delusionalgenerator.ViewModels.Factories.ExercisesDescriptionViewModelFactory;
import com.YaroslavGorbach.delusionalgenerator.Helpers.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;


public class ExercisesDescriptionFragment extends Fragment {
    private int mExId;
    private MaterialButton mStartExButton;
    private Exercise mExercise;
    private ExercisesDescriptionViewModel mViewModel;
    private int mExCategory;
    private Menu mMenu;
    private MaterialToolbar mToolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_description, container, false);
        /*инициализация вюх и провайдера*/
        mStartExButton = view.findViewById(R.id.button_start_ex_category_1);
        mToolbar = requireActivity().findViewById(R.id.toolbar_main_a);
        mMenu = mToolbar.getMenu();
        mExId = ExercisesDescriptionFragmentArgs.fromBundle(getArguments()).getExId();
        mExCategory = ExercisesDescriptionFragmentArgs.fromBundle(getArguments()).getExCategory();
        mViewModel = new ViewModelProvider(this, new ExercisesDescriptionViewModelFactory(
                getActivity().getApplication(),mExId)).get(ExercisesDescriptionViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Показ банера*/
        AdMob.showBanner(view.findViewById(R.id.adViewTabDescription));
        switch (mExCategory){
            case 1:
                getParentFragmentManager().beginTransaction()
                        .add(R.id.fragmentDescription, DescriptionCategory1Fragment.newInstance(mExId), null)
                        .commit();
                break;
            case 2:
                getParentFragmentManager().beginTransaction()
                        .add(R.id.fragmentDescription, DescriptionCategory2Fragment.newInstance(mExId), null)
                        .commit();
                break;
            case 3:
                getParentFragmentManager().beginTransaction()
                        .add(R.id.fragmentDescription, DescriptionCategory3Fragment.newInstance(mExId), null)
                        .commit();
                break;
        }



        /*Установка титла и иконки в туллбар в зависимости от того упражнение в избранном или нет*/
        mViewModel.getExerciseById(mExId).observe(getViewLifecycleOwner(), exercise -> {
            mExercise = exercise;
            mToolbar.setTitle(exercise.name);
            switch (exercise.favorite){
                case 1:
                    mMenu.getItem(0).setIcon(ContextCompat.getDrawable(
                            getContext(), R.drawable.ic_baseline_star));
                    break;

                case 0:
                    mMenu.getItem(0).setIcon(ContextCompat.getDrawable(
                            getContext(), R.drawable.ic_star_outline));
                    break;
            }

        });

        /*Оброботка нажатия на иконки тулбара*/
        mToolbar.setOnMenuItemClickListener(v->{
            switch (v.getItemId()){
                case R.id.add_to_favorite_item_1:
                    switch (mExercise.favorite){
                        case 0:
                            addExToFavorite(v, 1, R.drawable.ic_baseline_star);
                            break;
                        case 1:
                            addExToFavorite(v, 0, R.drawable.ic_star_outline);
                            break;
                    }
                    break;
                case R.id.show_chart_item_2:
                    NavDirections action = ExercisesDescriptionFragmentDirections.
                            actionExercisesDescriptionFragmentToStatisticsFragment().setExId(mExId);
                    Navigation.findNavController(getView()).navigate(action);
                    break;
            }
            return true;
        });

        /*При клике на кнопку "Начать" открываем подходящее упражнение упражнение*/
        mStartExButton.setOnClickListener(v -> {
            startEx(view);
        });

    }

    private void addExToFavorite(MenuItem v, int favorite, int icon) {
        mExercise.favorite = favorite;
        mViewModel.update(mExercise);
        v.setIcon(ContextCompat.getDrawable(
                getContext(), icon));
    }

    private void startEx(@NonNull View view) {
        if (mExercise.category == 1){
            NavDirections action = ExercisesDescriptionFragmentDirections.
                    actionExercisesDescriptionFragmentToExercisesCategory1Fragment().setIdEx(mExId);
            Navigation.findNavController(view).navigate(action);
        }else if(mExercise.category == 2){
            NavDirections action = ExercisesDescriptionFragmentDirections.
                    actionExercisesDescriptionFragmentToExercisesCategory2Fragment().setIdEx(mExId);
            Navigation.findNavController(view).navigate(action);
        } else if(mExercise.category == 3){
            NavDirections action = ExercisesDescriptionFragmentDirections.
                    actionExercisesDescriptionFragmentToExercisesCategory3Fragment().setExId(mExId);
            Navigation.findNavController(view).navigate(action);
        }
    }
}