package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.YaroslavGorbach.delusionalgenerator.util.getThemeColor.getAccentColor;
import static com.YaroslavGorbach.delusionalgenerator.util.getThemeColor.getBackgroundColor;


public class VocabularyFragment extends Fragment {
    private int mExId;
    private TextView mWordCounter_tv;
    private TextView mTimer_tv;
    private TextView mExShortDescription_tv;
    private ConstraintLayout mClickArea;
    private TextView mFinishText_tv;
    private int mCountValue;
    private MaterialButton mFinish_bt;
    private MaterialButton mNext_bt;
    private VocabularyVm mViewModel;
    private int mWordsNorm;
    private KonfettiView mKonfettiView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category_2, container, false);

        mWordCounter_tv = view.findViewById(R.id.worldCounterText);
        mTimer_tv = view.findViewById(R.id.timer);
        mExId = VocabularyFragmentArgs.fromBundle(requireArguments()).getIdEx();
        mExShortDescription_tv = view.findViewById(R.id.whatToDo);
        mClickArea = view.findViewById(R.id.clickAria);
        mFinishText_tv = view.findViewById(R.id.text_finished);
        mFinish_bt = view.findViewById(R.id.button_finish);
        mNext_bt = view.findViewById(R.id.button_next);
        mKonfettiView = view.findViewById(R.id.viewKonfetti);
        mViewModel = new ViewModelProvider(this, new ExerciseCategory2ViewModelFactory(
                requireActivity().getApplication(),mExId)).get(VocabularyVm.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*показ банера*/
        AdMob.showBanner(view.findViewById(R.id.banner));

        mViewModel.exShortDescription.observe(getViewLifecycleOwner(), exShortDesc->
                mExShortDescription_tv.setText(exShortDesc));
        mViewModel.exNormWords.observe(getViewLifecycleOwner(), normWords-> mWordsNorm = normWords);
        mViewModel.nextExName.observe(getViewLifecycleOwner(), nextExName-> mNext_bt.setText(nextExName));

        /*при клике по экрану инкремент значения*/
        mClickArea.setOnClickListener(v -> mViewModel.valuePlus());

        /*завершить упражнение при клике на кнопку*/
        mFinish_bt.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());

        /*следующее упражнение при нажатии на кнопку*/
        mNext_bt.setOnClickListener(v ->{
//            switch (mExId){
//                case 20:
//                    Navigation.findNavController(view).navigate(ExercisesCategory2FragmentDirections.
//                            actionExercisesCategory2FragmentToExercisesDescriptionFragment().
//                            setExCategory(2).setExId(21));
//                    break;
//                case 21:
//                    Navigation.findNavController(view).navigate(ExercisesCategory2FragmentDirections.
//                            actionExercisesCategory2FragmentToExercisesDescriptionFragment().
//                            setExCategory(2).setExId(22));
//                    break;
//                case 22:
//                    Navigation.findNavController(view).navigate(ExercisesCategory2FragmentDirections.
//                            actionExercisesCategory2FragmentToExercisesDescriptionFragment().
//                            setExCategory(2).setExId(20));
//                    break;
//            }
        });

        /*обзор актуального времени таймера*/
        mViewModel.currentTimeString.observe(getViewLifecycleOwner(), time -> mTimer_tv.setText(time));

        /*оброботка завершения таймера*/
        mViewModel.eventGameFinish.observe(getViewLifecycleOwner(), isFinished -> {
            if (isFinished) {
                timerFinished();
            }
        });

        /*обзор актуального значения*/
        mViewModel.countValue.observe(getViewLifecycleOwner(), value -> {
            if (value != null) {
                mCountValue = value;
                mWordCounter_tv.setText(getString(R.string.value, value));
            }

        });
    }

    private void timerFinished() {
        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(mWordCounter_tv);
        mTimer_tv.setVisibility(View.INVISIBLE);

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(mWordCounter_tv);
        mExShortDescription_tv.setVisibility(View.INVISIBLE);

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(mWordCounter_tv);
        mWordCounter_tv.setVisibility(View.INVISIBLE);

        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .playOn(mWordCounter_tv);
        mFinish_bt.setVisibility(View.VISIBLE);

        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .playOn(mWordCounter_tv);
        mNext_bt.setVisibility(View.VISIBLE);

        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .playOn(mFinishText_tv);
        mFinishText_tv.setVisibility(View.VISIBLE);

        if (mCountValue > mWordsNorm) {
            // it is good
            showKonfety();
            mFinishText_tv.setText(getString(R.string.finish_text, mCountValue,
                    String.valueOf(mWordsNorm), mViewModel.getGoodResoldString()));
        } else {
            // it is too bad
            mFinishText_tv.setText(getString(R.string.finish_text, mCountValue,
                    String.valueOf(mWordsNorm), mViewModel.getBadResoldString()));
        }

        mClickArea.setFocusable(false);
        mClickArea.setClickable(false);
        mClickArea.setBackgroundColor(getBackgroundColor(requireContext()));
    }

    private void showKonfety() {
                mKonfettiView.build()
                        .addColors(getAccentColor(requireContext()))
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                        .addSizes(new Size(12, 5f))
                        .setPosition(-50f, mKonfettiView.getWidth() + 50f, -50f, -50f)
                        .streamFor(300, 2000L);
            }
    }
