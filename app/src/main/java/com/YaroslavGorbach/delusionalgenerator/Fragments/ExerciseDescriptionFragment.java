package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.button.MaterialButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseDescriptionFragment extends Fragment {


    private static final String ARG_ID_EX = "ARG_ID_EX";


    private int mExId;
    private MaterialButton mStartExButton;
    private TextView mNameEx_tv;
    private TextView mAimEx_tv;
    private TextView mDescriptionEx_tv;


    public ExerciseDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param exId Parameter 1.
     * @return A new instance of fragment ExerciseDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseDescriptionFragment newInstance(int exId) {
        ExerciseDescriptionFragment fragment = new ExerciseDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_EX, exId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExId = getArguments().getInt(ARG_ID_EX, -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_description, container, false);
        mStartExButton = view.findViewById(R.id.button_start_ex_category_1);
        mNameEx_tv = view.findViewById(R.id.exercises_name);
        mAimEx_tv = view.findViewById(R.id.textView_aim_ex);
        mDescriptionEx_tv = view.findViewById(R.id.textView_description_ex);



        switch (mExId){

            case  1:
                mNameEx_tv.setText("Лингвистические пирамиды");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_1));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_1));
                break;


            case 2:
                mNameEx_tv.setText("Чем ворон похож на стул");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_2));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_2));
                break;

            case 3:
                mNameEx_tv.setText("Чем ворон похож на стул (чувства)");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_3));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_3));
                break;

            case 4:
                mNameEx_tv.setText("Продвинутое сявязывание");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_4));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_4));
                break;

            case 5:
                mNameEx_tv.setText("О чем вижу, о том и пою");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_5));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_5));
                break;

            case 6:
                mNameEx_tv.setText("Другие варианты сокращений");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_6));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_6));

                break;
            case 7:
                mNameEx_tv.setText("Волшебный нейминг");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_7));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_7));
                break;
            case 8:
                mNameEx_tv.setText("Купля-продажа");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_8));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_8));
                break;
            case 9:
                mNameEx_tv.setText("Вспомнить все");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_9));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_9));
                break;
            case 10:
                mNameEx_tv.setText("В соавторстве с Далем");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_10));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_10));
                break;
            case 11:
                mNameEx_tv.setText("Тест Роршаха");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_11));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_11));
                break;
            case 12:
                mNameEx_tv.setText("Хуже уже не будет");
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_12));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_12));
                break;
        }

        mStartExButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.exercise_description_container,
                    ExercisesCategory1Fragment.newInstance(mExId)).commit();
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}