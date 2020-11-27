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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;


public class ExercisesDescriptionFragment extends Fragment {


    private int mExId;
    private MaterialButton mStartExButton;
    private TextView mAimEx_tv;
    private TextView mDescriptionEx_tv;
    private Exercise mExercise;
    private ExercisesViewModel mViewModel;
    private Menu mMenu;
    private MaterialToolbar mToolbar;

    public ExercisesDescriptionFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ExercisesDescriptionFragment newInstance() {
        return new ExercisesDescriptionFragment();
    }

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
        mStartExButton = view.findViewById(R.id.button_start_ex_category_1);
        mAimEx_tv = view.findViewById(R.id.textView_aim_ex);
        mDescriptionEx_tv = view.findViewById(R.id.textView_description_ex);
        mToolbar = view.findViewById(R.id.toolbar_description);
        mToolbar.inflateMenu(R.menu.menu_description);
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(
                getContext(), R.drawable.ic_arrow_back));
        mMenu = mToolbar.getMenu();
        mViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        mExId = ExercisesDescriptionFragmentArgs.fromBundle(getArguments()).getExId();

        /*Установка иконки в туллбар в зависимости от того упражнение в избранном или нет*/
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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Показ банера*/
        AdView mAdView;
        mAdView = view.findViewById(R.id.adViewTab1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        /*Оброботка нажатия на иконки тулбара*/
        mToolbar.setOnMenuItemClickListener(v->{
            switch (v.getItemId()){
                case R.id.add_to_favorite:

                    switch (mExercise.favorite){
                        case 0:
                            mExercise.favorite = 1;
                            mViewModel.update(mExercise);
                            v.setIcon(ContextCompat.getDrawable(
                                    getContext(), R.drawable.ic_baseline_star));
                            break;

                        case 1:
                            mExercise.favorite = 0;
                            mViewModel.update(mExercise);
                            v.setIcon(ContextCompat.getDrawable(
                                    getContext(), R.drawable.ic_star_outline));
                            break;
                    }

                    break;

                case R.id.delete_files:
                    NavDirections action = ExercisesDescriptionFragmentDirections.
                            actionExercisesDescriptionFragmentToStatisticsFragment().setExId(mExId);
                    Navigation.findNavController(view).navigate(action);
                    break;
            }

            return true;
        });

        /*В зависимости от id упржанения устанавливаем соответствующий текст для описания упражнения*/
        switch (mExId){

            case  1:
                mToolbar.setTitle("Лингвистические пирамиды");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_1));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_1));
                break;


            case 2:
                mToolbar.setTitle("Чем ворон похож на стол");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_2));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_2));
                break;

            case 3:
                mToolbar.setTitle("Чем ворон похож на стул (чувства)");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_3));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_3));
                break;

            case 4:
                mToolbar.setTitle("Продвинутое связывание");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_4));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_4));
                break;

            case 5:
                mToolbar.setTitle("О чем вижу, о том и пою");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_5));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_5));
                break;

            case 6:
                mToolbar.setTitle("Другие варианты сокращений");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_6));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_6));
                break;
            case 7:
                mToolbar.setTitle("Волшебный нейминг");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_7));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_7));
                break;
            case 8:
                mToolbar.setTitle("Купля - продажа");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_8));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_8));
                break;
            case 9:
                mToolbar.setTitle("Вспомнить все");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_9));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_9));
                break;
            case 10:
                mToolbar.setTitle("В соавторстве с Далем");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_10));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_10));
                break;
            case 11:
                mToolbar.setTitle("Тест Роршаха");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_11));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_11));
                break;
            case 12:
                mToolbar.setTitle("Хуже уже не будет");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_12));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_12));
                break;

            case 20:
                mToolbar.setTitle("Существительные");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_20));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_20));
                break;

            case 21:
                mToolbar.setTitle("Прилагательные");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_21));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_21));
                break;

            case 22:
                mToolbar.setTitle("Глаголы");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_22));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_22));
                break;

            case 30:
                mToolbar.setTitle("Простые скороговорки");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_30));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_30));
                break;

            case 31:
                mToolbar.setTitle("Сложные скороговорки");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_31));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_31));
                break;

            case 32:
                mToolbar.setTitle("Очень сложные скороговорки");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_32));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_32));
                break;
        }

        /*При клике на кнопку "Начать" открываем упражнение*/

        mStartExButton.setOnClickListener(v -> {
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

        });

        /*Обработка нажатия стрелки назад*/
        mToolbar.setNavigationOnClickListener(v->{
            Navigation.findNavController(view).popBackStack();
        });

    }
}