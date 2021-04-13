package com.YaroslavGorbach.delusionalgenerator.screen.exercise.tongue_twisters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking.SpeakingFragmentArgs;
import com.YaroslavGorbach.delusionalgenerator.util.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class TongueTwisterFragment extends Fragment {

    private TextView mTwisters_tv;
    private ImageButton mNextTwistButton;
    private TextView mTongueTwisterHelp_tv;
    private int mExId;
    private long mStartExTime;
    private TongueTwisterVm mViewModel;


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
        mExId = SpeakingFragmentArgs.fromBundle(requireArguments()).getIdEx();
        mViewModel = new ViewModelProvider(this, new ExerciseCategory3ViewModelFactory(requireActivity().getApplication(),
                mExId)).get(TongueTwisterVm.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*показ банера*/
        AdMob.showBanner(view.findViewById(R.id.banner));

        /*оброботка клика на кнопку для показа слудующей скороговорки*/
        mNextTwistButton.setOnClickListener(v->{
            YoYo.with(Techniques.Tada)
                    .duration(400)
                    .playOn(mNextTwistButton);
            mViewModel.onClick();
        });

        /*в зависимости от количества кликов устанавливаем инструкцию и скороговорку*/
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}