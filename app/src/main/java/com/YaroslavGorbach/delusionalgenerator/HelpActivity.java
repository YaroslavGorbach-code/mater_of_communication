package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    int mIdEx;
    private TextView mExName;
    private TextView mAimOfExercise;
    private TextView mHawToPerformTheExercise;
    public static final String EXTRA_ID = "EXTRA_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initializeComponents();

        mToolbar.setNavigationOnClickListener(v->{

            finish();
        });

        switch (mIdEx){
            case 1:
                mExName.setText("Лингвистические пирамиды");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_1));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_1));
                break;
            case 2:

                mExName.setText("Чем ворон похож на стол");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_2));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_2));
                break;
            case 3:
                mExName.setText("Чем ворон похож на стол (чувства)");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_3));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_3));
                break;
            case 4:
                mExName.setText("Продвинутое связывание");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_4));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_4));
                break;
            case 5:
                mExName.setText("О чем вижу, о том и пою");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_5));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_5));
                break;
            case 6:
                mExName.setText("Другие варианты сокращений");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_6));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_6));
                break;
            case 7:
                mExName.setText("Волшебный нейминг");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_7));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_7));
                break;
            case 8:
                mExName.setText("Купля-продажа");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_8));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_8));
                break;
            case 9:
                mExName.setText("Вспомнить все");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_9));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_9));
                break;
            case 10:
                mExName.setText("В соавторстве с Далем");
                mAimOfExercise.setText(getResources().getString(R.string.Aim_of_exercise_10));
                mHawToPerformTheExercise.setText(getResources().getString(R.string.Haw_to_perform_exercise_10));
                break;
        }

    }

    private void initializeComponents(){
        mToolbar = findViewById(R.id.toolbar_notification);
        mIdEx = getIntent().getIntExtra(EXTRA_ID,-1);
        mExName = findViewById(R.id.exercises_name);
        mAimOfExercise = findViewById(R.id.aim_of_exercise);
        mHawToPerformTheExercise = findViewById(R.id.haw_to_perform);

    }

    private void setTheme(){
        String color = Repo.getInstance(HelpActivity.this).getThemeState();
        switch (color){

            case "blue":

                setTheme(R.style.AppTheme_blue);

                break;

            case "green":

                setTheme(R.style.AppTheme_green);
                break;

            case "orange":

                setTheme(R.style.AppTheme_orange);
                break;

            case "red":

                setTheme(R.style.AppTheme_red);
                break;

            case "purple":

                setTheme(R.style.AppTheme_purple);
                break;

        }
    }

    }

