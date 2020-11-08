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
 * Use the {@link ExercisesDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesDescriptionFragment extends Fragment {


    private static final String ARG_ID_EX = "ARG_ID_EX";


    private int mExId;
    private MaterialButton mStartExButton;
    private TextView mAimEx_tv;
    private TextView mDescriptionEx_tv;


    public ExercisesDescriptionFragment() {
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
    public static ExercisesDescriptionFragment newInstance(int exId) {
        ExercisesDescriptionFragment fragment = new ExercisesDescriptionFragment();
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
        mAimEx_tv = view.findViewById(R.id.textView_aim_ex);
        mDescriptionEx_tv = view.findViewById(R.id.textView_description_ex);



        switch (mExId){

            case  1:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_1));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_1));
                break;


            case 2:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_2));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_2));
                break;

            case 3:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_3));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_3));
                break;

            case 4:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_4));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_4));
                break;

            case 5:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_5));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_5));
                break;

            case 6:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_6));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_6));

                break;
            case 7:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_7));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_7));
                break;
            case 8:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_8));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_8));
                break;
            case 9:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_9));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_9));
                break;
            case 10:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_10));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_10));
                break;
            case 11:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_11));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_11));
                break;
            case 12:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_12));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_12));
                break;

            case 20:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_20));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_20));
                break;

            case 21:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_21));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_21));
                break;

            case 22:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_22));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_22));
                break;

            case 30:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_30));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_30));
                break;

            case 31:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_31));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_31));
                break;

            case 32:
                mAimEx_tv.setText(getResources().getString(R.string.Aim_of_exercise_32));
                mDescriptionEx_tv.setText(getResources().getString(R.string.Haw_to_perform_exercise_32));
                break;
        }

        mStartExButton.setOnClickListener(v -> {
            if (mExId<20 && mExId > 0){
                getParentFragmentManager().beginTransaction().replace(R.id.exercise_description_container,
                        ExercisesCategory1Fragment.newInstance(mExId)).commit();

            }else if(mExId > 19 && mExId < 23){
                getParentFragmentManager().beginTransaction().replace(R.id.exercise_description_container,
                        ExercisesCategory2Fragment.newInstance(mExId)).commit();

            } else if(mExId > 29 && mExId < 33){
                getParentFragmentManager().beginTransaction().replace(R.id.exercise_description_container,
                         ExercisesCategory3Fragment.newInstance(mExId)).commit();
        }

        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}