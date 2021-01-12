package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExerciseCategory3ViewModel;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.Factories.ExerciseCategory3ViewModelFactory;
import com.YaroslavGorbach.delusionalgenerator.Helpers.AdMob;
import com.YaroslavGorbach.delusionalgenerator.Helpers.DateAndTime;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.MaterialToolbar;

public class ExercisesCategory3Fragment extends Fragment {

    private TextView mTwisters_tv;
    private ImageButton mNextTwistButton;
    private TextView mTongueTwisterHelp_tv;
    private int mExId;
    private long mStartExTime;
    private MaterialToolbar mMaterialToolbar;
    private ExerciseCategory3ViewModel mViewModel;

    public static ExercisesCategory3Fragment newInstance() {
        return new ExercisesCategory3Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercises_category_3, container, false);
        mTwisters_tv = view.findViewById(R.id.twisters);
        mNextTwistButton = view.findViewById(R.id.nextTwist);
        mTongueTwisterHelp_tv = view.findViewById(R.id.tongue_twister_help);
        mStartExTime = SystemClock.elapsedRealtime();
        mExId = ExercisesCategory3FragmentArgs.fromBundle(getArguments()).getExId();
        mMaterialToolbar = view.findViewById(R.id.toolbar_ex_category_3);
        mMaterialToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mViewModel = new ViewModelProvider(this, new ExerciseCategory3ViewModelFactory(getActivity().getApplication(),
                mExId)).get(ExerciseCategory3ViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMaterialToolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        /*показ банера*/
        AdMob.showBanner(view.findViewById(R.id.adViewTabEx3));

        /*установка текста в туллбар*/
        setToolbarTitle();

        /*оброботка клика на кнопку для показа слудующей скороговорки*/
        mNextTwistButton.setOnClickListener(v->{
            mViewModel.onClick();
        });

        /*в зависимости од количества кликов устанавливаем инструкцию и скороговорку*/
        mViewModel.clickCounter.observe(getViewLifecycleOwner(), clickValue -> {
            switch (clickValue) {
                case 0:
                case 5:
                    mTongueTwisterHelp_tv.setText("Проговаривайте текст медленно");
                    setTwist();
                    break;
                case 1:
                    mTongueTwisterHelp_tv.setText("Беззвучно произнесите скороговорку. Движениями губ");
                    break;
                case 2:
                    mTongueTwisterHelp_tv.setText("Произнесите текст в пол голоса, шепотом");
                    break;
                case 3:
                    mTongueTwisterHelp_tv.setText("Произнесите вслух, громко, но все ещё медленно и четко");
                    break;
                case 4:
                    mTongueTwisterHelp_tv.setText("Теперь пробуйте произносить текст в разных стилях, с разной интонацией, с разной скоростью");
                    break;
            }
        });
    }

    private void setTwist(){
        YoYo.with(Techniques.FadeIn)
                .duration(400)
                .playOn(mTwisters_tv);
        mTwisters_tv.setText(mViewModel.getTwist());
    }

    private void setToolbarTitle() {
        switch (mExId){
            case 30:
                mMaterialToolbar.setTitle("Простые скороговорки");
                break;
            case 31:
                mMaterialToolbar.setTitle("Сложные скороговорки");
                break;
            case 32:
                mMaterialToolbar.setTitle("Очень сложные скороговорки");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DateAndTime.insertDataToStatistic(getContext(), mExId, mViewModel.numberOfTwisters.getValue(), mStartExTime);
    }
}