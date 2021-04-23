package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.os.SystemClock;
import android.widget.Toast;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingBinding;

public class SpeakingView {
    interface Callback{
        void onUp();
        void onNext();
        void onStartStopRecord();
    }
    private final FragmentSpeakingBinding mBinding;

    public SpeakingView(FragmentSpeakingBinding binding, Callback callback){
        mBinding = binding;
        mBinding.chronometer.start();
        mBinding.chronometerOneWord.start();

        binding.toolbar.setNavigationOnClickListener(v -> callback.onUp());
        binding.startStopRecord.setOnClickListener(v -> callback.onStartStopRecord());
        binding.next.setOnClickListener(v -> callback.onNext());
    }

    public void setShortDesc(String desc){
        mBinding.shortDesc.setText(desc);
    }

    public void setTitle(String title){
        mBinding.toolbar.setTitle(title);
    }

    public void setWord(String word){
        mBinding.chronometerOneWord.setBase(SystemClock.elapsedRealtime());
        mBinding.word.setText(word);
    }


    public void changeButtonImage(boolean isRecording){
        if (isRecording){
            mBinding.startStopRecord.setImageResource(R.drawable.ic_voice_recording);
        }else {
            mBinding.startStopRecord.setImageResource(R.drawable.ic_voice_stop);
            Toast.makeText(mBinding.getRoot().getContext(), "Запись сохранена", Toast.LENGTH_SHORT).show();
        }
    }
}
