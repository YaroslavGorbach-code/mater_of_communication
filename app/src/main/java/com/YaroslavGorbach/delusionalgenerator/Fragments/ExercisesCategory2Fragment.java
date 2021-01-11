package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExerciseCategory1ViewModel;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.ExerciseCategory2ViewModel;
import com.YaroslavGorbach.delusionalgenerator.Helpers.AdMob;
import com.YaroslavGorbach.delusionalgenerator.Helpers.DateAndTime;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.MaterialToolbar;


public class ExercisesCategory2Fragment extends Fragment {

    private int mExId;
    private boolean mIsRunning;
    private TextView mWordCounter_tv;
    private TextView mTimer_tv;
    private TextView mWhatTodo_tv;
    private ConstraintLayout mClickAria;
    private TextView mRecord_tv;
    private int mCountValue;

    private MaterialToolbar mMaterialToolbar;
    private ExerciseCategory2ViewModel mViewModel;


    public static ExercisesCategory2Fragment newInstance() {
        return new ExercisesCategory2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises_category_2, container, false);

        mWordCounter_tv = view.findViewById(R.id.worldCounterText);
        mTimer_tv = view.findViewById(R.id.timer);
        mExId = ExercisesCategory2FragmentArgs.fromBundle(getArguments()).getIdEx();
        mMaterialToolbar = view.findViewById(R.id.toolbar_ex_category_2);
        mMaterialToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mWhatTodo_tv = view.findViewById(R.id.whatToDo);
        mClickAria = view.findViewById(R.id.clickAria);
        mRecord_tv = view.findViewById(R.id.worldRecordText);
        mViewModel = new ViewModelProvider(this).get(ExerciseCategory2ViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setExDescription();

        /*показ банера*/
        AdMob.showBanner(view.findViewById(R.id.adViewTabEx2));

        /*завершить упражнение при клике на стрелку назад*/
        mMaterialToolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        /*при клике по экрану инкремент значения*/
        mClickAria.setOnClickListener(v -> {
            if (!mIsRunning){
                mViewModel.valuePlus();
            }
        });

        /*обзор актуального времени таймера*/
        mViewModel.currentTimeString.observe(getViewLifecycleOwner(), time -> {
            mTimer_tv.setText(time);
        });

        /*оброботка завершения таймера*/
        mViewModel.eventGameFinish.observe(getViewLifecycleOwner(), isFinished ->{
            mIsRunning = isFinished;
            if (isFinished){
                //loadConfeti()
                DateAndTime.insertDataToStatistic(getContext(), mExId, mCountValue, 10);
                if (Repo.getInstance(getContext()).getMaxWorldCount(mExId)!=0){
                    mRecord_tv.setVisibility(View.VISIBLE);
                    mRecord_tv.setText(getString(R.string.record, Repo.getInstance(getContext()).getMaxWorldCount(mExId)));
                }
            }
        });

        /*обзор актуального значения*/
        mViewModel.countValue.observe(getViewLifecycleOwner(), value -> {
            if (value != null){
                mCountValue = value;
                mWordCounter_tv.setText(getString(R.string.value, value));
            }

        });
    }

    private void setExDescription() {
        switch (mExId){
        case 20:
        mMaterialToolbar.setTitle("Существительные");
        mWhatTodo_tv.setText("Нозовите как можно больше существительных(Кто? Что?). После каждого названого слова, нажмите" +
                " на экран, для учета результата");
        break;
        case 21:
        mMaterialToolbar.setTitle("Прилагательные");
            mWhatTodo_tv.setText("Нозовите как можно больше прилагательных(Какой? Какое?). После каждого названого слова, нажмите" +
                    " на экран, для учета результата");
        break;
        case 22:
        mMaterialToolbar.setTitle("Глаголы");
            mWhatTodo_tv.setText("Нозовите как можно больше глаголов(Что делать? Что сделать?). После каждого названого слова, нажмите" +
                    " на экран, для учета результата");
        break;
        }
    }
}