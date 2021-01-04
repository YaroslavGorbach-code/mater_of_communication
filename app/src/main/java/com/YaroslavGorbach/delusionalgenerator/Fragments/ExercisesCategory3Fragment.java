package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Helpers.DateAndTime;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Random;

public class ExercisesCategory3Fragment extends Fragment {

    private TextView mTwisters_tv;
    private ImageButton mNextTwistButton;
    private TextView mTongueTwisterHelp_tv;
    private String[] mTwists = {};
    private int mClickCounter = 1;
    private Random r = new Random();
    private int mExId;
    private long mStartExTime;
    private int mNumber_of_tongue_twisters = 0;
    private MaterialToolbar mMaterialToolbar;


    public ExercisesCategory3Fragment() {
        // Required empty public constructor
    }

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

        /*получаем слова из резурсов*/
        getTwisters();
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTwist();
        mMaterialToolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        /*установка тексна в туллбар*/
        setToolbarTitle();

        /*оброботка клика на кнопку для показа слудующей скороговорки*/
        mNextTwistButton.setOnClickListener(v->{
            nextTwisterButtonClickListener();
        });

        /*показ банера*/
        AdView mAdView = view.findViewById(R.id.adViewTabEx3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void nextTwisterButtonClickListener() {
            switch (mClickCounter){
                case 1:
                    mTongueTwisterHelp_tv.setText("Беззвучно произнесите скороговорку. Движениями губ");
                    mClickCounter++;
                    break;
                case 2:
                    mTongueTwisterHelp_tv.setText("Произнесите текст в пол голоса, шепотом");
                    mClickCounter++;
                    break;
                case 3:
                    mTongueTwisterHelp_tv.setText("Произнесите вслух, громко, но все ещё медленно и четко");
                    mClickCounter++;
                    break;
                case 4:
                    mTongueTwisterHelp_tv.setText("Теперь пробуйте произносить текст в разных стилях, с раной интонацией, с разной скоростью");
                    mClickCounter++;
                    break;
                case 5:
                    setTwist();
                    mClickCounter = 1;
                    mTongueTwisterHelp_tv.setText("Проговаривайте текст медленно");
                    break;
            }
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

    private void getTwisters() {
        switch (mExId){
            case 30:
                mTwists = getResources().getStringArray(R.array.twisters_easy);
                break;
            case 31:
                mTwists = getResources().getStringArray(R.array.twisters_hard);
                break;
            case 32:
                mTwists = getResources().getStringArray(R.array.twisters_very_hard);
                break;
        }
    }

    private void setTwist() {
        YoYo.with(Techniques.FadeIn)
                .duration(400)
                .playOn(mTwisters_tv);
        mTwisters_tv.setText(mTwists[r.nextInt(mTwists.length)]);
        mNumber_of_tongue_twisters++;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DateAndTime.insertDataToStatistic(getContext(), mExId, mNumber_of_tongue_twisters, mStartExTime);
    }
}