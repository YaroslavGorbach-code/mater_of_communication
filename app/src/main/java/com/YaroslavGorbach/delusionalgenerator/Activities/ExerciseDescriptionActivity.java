package com.YaroslavGorbach.delusionalgenerator.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.YaroslavGorbach.delusionalgenerator.Database.Models.Exercise;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExercisesViewModel;
import com.YaroslavGorbach.delusionalgenerator.Fragments.ExercisesDescriptionFragment;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.MaterialToolbar;

public class ExerciseDescriptionActivity extends AppCompatActivity {
    private MaterialToolbar mToolbar;
    private int mExId;
    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";
    private Exercise mExercise;
    private ExercisesViewModel mViewModel;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exersice_description2);
        mViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        mToolbar = findViewById(R.id.toolbar_ex_category_1);
        mToolbar.inflateMenu(R.menu.menu_description);
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(
                ExerciseDescriptionActivity.this, R.drawable.ic_arrow_back));
        mExId = getIntent().getIntExtra(EXTRA_ID_EX, -1);
        mMenu = mToolbar.getMenu();


        /*Показ банера*/
        AdView mAdView;
        mAdView = findViewById(R.id.adViewTab1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*Установка иконки в туллбар в зависимости от того упражнение в избранном или нет*/
        mViewModel.getExerciseById(mExId).observe(this, exercise -> {
            mExercise = exercise;

            switch (exercise.favorite){
                case 1:
                 mMenu.getItem(0).setIcon(ContextCompat.getDrawable(
                            ExerciseDescriptionActivity.this, R.drawable.ic_baseline_star));
                    break;

                case 0:
                    mMenu.getItem(0).setIcon(ContextCompat.getDrawable(
                            ExerciseDescriptionActivity.this, R.drawable.ic_star_outline));
                    break;
            }
        });



        /*В зависимости от айди упражнения мы выбираем потходяшии слова и иницыализируем наши view*/
        switch (mExId){

            case 1:
                mToolbar.setTitle("Лингвистические пирамиды");
                break;
            case 2:
                mToolbar.setTitle("Чем ворон похож на стул");
                break;
            case 3:
                mToolbar.setTitle("Чем ворон похож на стул (чувства)");
                break;
            case 4:
                mToolbar.setTitle("Продвинутое сявязывание");
                break;
            case 5:
                mToolbar.setTitle("О чем вижу, о том и пою");
                break;
            case 6:
                mToolbar.setTitle("Другие варианты сокращений");
                break;
            case 7:
                mToolbar.setTitle("Волшебный нейминг");
                break;
            case 8:
                mToolbar.setTitle("Купля-продажа");
                break;
            case 9:
                mToolbar.setTitle("Вспомнить все");
                break;
            case 10:
                mToolbar.setTitle("В соавторстве с Далем");
                break;
            case 11:
                mToolbar.setTitle("Тест Роршаха");
                break;
            case 12:
                mToolbar.setTitle("Хуже уже не будет");
                break;
            case 20:
                mToolbar.setTitle("Существительные");
                break;
            case 21:
                mToolbar.setTitle("Прилагательные");
                break;
            case 22:
                mToolbar.setTitle("Глаголы");
                break;
            case 30:
                mToolbar.setTitle("Простые скороговорки");
                break;
            case 31:
                mToolbar.setTitle("Сложные скороговорки");
                break;
            case 32:
                mToolbar.setTitle("Очень сложные скороговорки");
                break;

        }
        /*Устновка фрагмента с описанием для упражнения*/
        getSupportFragmentManager().beginTransaction().replace(R.id.exercise_description_container,
                ExercisesDescriptionFragment.newInstance(mExId)).commit();

        /*Оброботка нажатия на иконки тулбара*/
        mToolbar.setOnMenuItemClickListener(v->{
            switch (v.getItemId()){
                case R.id.add_to_favorite:

                    switch (mExercise.favorite){
                        case 0:
                            mExercise.favorite = 1;
                            mViewModel.update(mExercise);
                           v.setIcon(ContextCompat.getDrawable(
                                 ExerciseDescriptionActivity.this, R.drawable.ic_baseline_star));
                            break;

                        case 1:
                            mExercise.favorite = 0;
                            mViewModel.update(mExercise);
                            v.setIcon(ContextCompat.getDrawable(
                                    ExerciseDescriptionActivity.this, R.drawable.ic_star_outline));
                            break;
                    }

                    break;
                case R.id.open_statistic_ex:
                    startActivity(new Intent(this, Statistics_activity.class)
                            .putExtra(Statistics_activity.EXTRA_ID_EX, mExId));
                    break;
            }

            return true;
        });
                /*Остановка активити при нажатии на стрелку назад*/
                mToolbar.setNavigationOnClickListener(v->{
                   finish();
                });

    }


}