package com.YaroslavGorbach.delusionalgenerator.Activityes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.YaroslavGorbach.delusionalgenerator.Fragments.ExerciseDescriptionFragment;
import com.YaroslavGorbach.delusionalgenerator.Fragments.ExercisesCategory1Fragment;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class ExerciseDescriptionActivity extends AppCompatActivity {
    private MaterialToolbar mToolbar;
    private int mExId;
    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exersice_description2);
        mToolbar = findViewById(R.id.toolbar_ex_category_1);
        mToolbar.inflateMenu(R.menu.menu_ex_desk);
        mExId = getIntent().getIntExtra(EXTRA_ID_EX, -1);

        /*Показ банера*/
        AdView mAdView;
        mAdView = findViewById(R.id.adViewTab1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.exercise_description_container,
                ExerciseDescriptionFragment.newInstance(mExId)).commit();

        /*Оброботка нажатия на кнопку помощи по упражнению.*/
        mToolbar.setOnMenuItemClickListener(v->{
            startActivity(new Intent(this, HelpActivity.class)
                    .putExtra(HelpActivity.EXTRA_ID, mExId));
            return true;
        });
                /*Остановка активити при нажатии на стрелку назад*/
                mToolbar.setNavigationOnClickListener(v->{
                   finish();
                });

    }


}