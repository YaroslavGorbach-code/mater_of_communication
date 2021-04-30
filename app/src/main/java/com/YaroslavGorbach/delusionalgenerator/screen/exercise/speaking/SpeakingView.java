package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import androidx.core.util.Pair;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingBinding;

import java.util.Objects;

public class SpeakingView {
    interface Callback{
        void onUp();
        void onNext();
        void onStartStopRecord();
    }
    private final FragmentSpeakingBinding mBinding;
    private final Callback mCallback;

    public SpeakingView(FragmentSpeakingBinding binding, Callback callback){
        mBinding = binding;
        mCallback = callback;
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

    public void setDoneAndAim(Pair<Integer, Integer> doneAndAim){
        mBinding.aimAndDone.setVisibility(View.VISIBLE);
        if (doneAndAim.first == doneAndAim.second - 1){
            mBinding.next.setImageResource(R.drawable.ic_done);
        }
        if (Objects.equals(doneAndAim.first, doneAndAim.second)){
            mCallback.onUp();
        }
        mBinding.aimAndDone.setText(doneAndAim.first +"/"+doneAndAim.second);
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
