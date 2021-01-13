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
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesDescriptionViewModel;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.Factories.ExercisesDescriptionViewModelFactory;
import com.YaroslavGorbach.delusionalgenerator.Helpers.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;


public class ExercisesDescriptionFragment extends Fragment {
    private int mExId;
    private MaterialButton mStartExButton;
    private TextView mAimEx_tv;
    private TextView mDescriptionEx_tv;
    private Exercise mExercise;
    private ExercisesDescriptionViewModel mViewModel;
    private Menu mMenu;
    private MaterialToolbar mToolbar;

    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.bttm_nav).setVisibility(View.GONE);
        getActivity().findViewById(R.id.toolbar_main_a).setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_description, container, false);
        /*инициализация вюх и провайдера*/
        mStartExButton = view.findViewById(R.id.button_start_ex_category_1);
        mAimEx_tv = view.findViewById(R.id.textView_aim_ex);
        mDescriptionEx_tv = view.findViewById(R.id.textView_description_ex);
        mToolbar = view.findViewById(R.id.toolbar_description);
        mToolbar.inflateMenu(R.menu.menu_description);
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(
                view.getContext(), R.drawable.ic_arrow_back));
        mMenu = mToolbar.getMenu();
        mExId = ExercisesDescriptionFragmentArgs.fromBundle(getArguments()).getExId();
        mViewModel = new ViewModelProvider(this, new ExercisesDescriptionViewModelFactory(
                getActivity().getApplication(),mExId)).get(ExercisesDescriptionViewModel.class);

        /*Установка иконки в туллбар в зависимости от того упражнение в избранном или нет*/
        setFavoriteToolbarIcon();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Показ банера*/
        AdMob.showBanner(view.findViewById(R.id.adViewTabDescription));

        mViewModel.exName.observe(getViewLifecycleOwner(), name->{
            mToolbar.setTitle(name);
        });

        mViewModel.exAim.observe(getViewLifecycleOwner(), aim->{
            mAimEx_tv.setText(aim);
        });

        mViewModel.exDescription.observe(getViewLifecycleOwner(), description->{
            mDescriptionEx_tv.setText(description);
        });

        /*Оброботка нажатия на иконки тулбара*/
        mToolbar.setOnMenuItemClickListener(v->{
            switch (v.getItemId()){
                case R.id.add_to_favorite:
                    switch (mExercise.favorite){
                        case 0:
                            addExToFavorite(v, 1, R.drawable.ic_baseline_star);
                            break;
                        case 1:
                            addExToFavorite(v, 0, R.drawable.ic_star_outline);
                            break;
                    }
                    break;

                case R.id.show_chart:
                    NavDirections action = ExercisesDescriptionFragmentDirections.
                            actionExercisesDescriptionFragmentToStatisticsFragment().setExId(mExId);
                    Navigation.findNavController(view).navigate(action);
                    break;
            }
            return true;
        });
        
        /*При клике на кнопку "Начать" открываем подходящее упражнение упражнение*/
        mStartExButton.setOnClickListener(v -> {
            startEx(view);
        });

        /*Обработка нажатия стрелки назад*/
        mToolbar.setNavigationOnClickListener(v->{
            Navigation.findNavController(view).popBackStack();
        });
    }

    private void addExToFavorite(MenuItem v, int favorite, int icon) {
        mExercise.favorite = favorite;
        mViewModel.update(mExercise);
        v.setIcon(ContextCompat.getDrawable(
                getContext(), icon));
    }

    private void startEx(@NonNull View view) {
        if (mExId<20 && mExId > 0){
            NavDirections action = ExercisesDescriptionFragmentDirections.
                    actionExercisesDescriptionFragmentToExercisesCategory1Fragment().setIdEx(mExId);
            Navigation.findNavController(view).navigate(action);
        }else if(mExId > 19 && mExId < 23){
            NavDirections action = ExercisesDescriptionFragmentDirections.
                    actionExercisesDescriptionFragmentToExercisesCategory2Fragment().setIdEx(mExId);
            Navigation.findNavController(view).navigate(action);
        } else if(mExId > 29 && mExId < 33){
            NavDirections action = ExercisesDescriptionFragmentDirections.
                    actionExercisesDescriptionFragmentToExercisesCategory3Fragment().setExId(mExId);
            Navigation.findNavController(view).navigate(action);
        }
    }

    private void setFavoriteToolbarIcon() {
        mViewModel.getExerciseById(mExId).observe(getViewLifecycleOwner(), exercise -> {
            mExercise = exercise;
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
    }
}