package com.YaroslavGorbach.delusionalgenerator.screen.exercise.speaking;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import androidx.core.util.Pair;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentSpeakingBinding;
import com.YaroslavGorbach.delusionalgenerator.util.ViewUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpeakingView {
    interface Callback{
        void onUp();
        void onNext();
        void onStartStopRecord();
    }

    private final FragmentSpeakingBinding mBinding;
    private final Callback mCallback;
    private final Handler mHandler= new Handler(Looper.getMainLooper());

    public SpeakingView(FragmentSpeakingBinding binding, Callback callback){
        mBinding = binding;
        mCallback = callback;
        mBinding.chronometer.start();
        mBinding.chronometerOneWord.start();

        binding.toolbar.setNavigationOnClickListener(v -> callback.onUp());
        binding.startStopRecord.setOnClickListener(v -> {
            v.setClickable(false);
            callback.onStartStopRecord();
            mHandler.postDelayed(() -> v.setClickable(true),500);
        });
        binding.next.setOnClickListener(v -> callback.onNext());
        Map<String, String> d = new HashMap<String, String>();
    }

    public void setShortDesc(String desc){
        mBinding.shortDesc.setText(desc);
    }

    public void setTitle(String title){
        mBinding.toolbar.setTitle(title);
    }

    public void setWord(String word){
        mBinding.chronometerOneWord.setBase(SystemClock.elapsedRealtime());
        mBinding.word.setTextSize(ViewUtil.getCorrectTextSize(word.length()));
        mBinding.word.setText(word);
    }

    @SuppressLint("SetTextI18n")
    public void setDoneAndAim(Pair<Integer, Integer> doneAndAim){
        mBinding.aimAndDoneLayout.setVisibility(View.VISIBLE);
        if (doneAndAim.first == doneAndAim.second - 1){
            mBinding.next.setImageResource(R.drawable.ic_done);
        }
        if (Objects.equals(doneAndAim.first, doneAndAim.second)){
            mHandler.postDelayed(mCallback::onUp,500);
        }
        mBinding.aimAndDoneText.setText(doneAndAim.first +"/"+doneAndAim.second);
    }

    public void changeButtonImage(boolean isRecording){
        if (isRecording){
            mBinding.startStopRecord.setImageResource(R.drawable.ic_voice_recording);
        }else {
            mBinding.startStopRecord.setImageResource(R.drawable.ic_voice_stop);
            Toast.makeText(mBinding.getRoot().getContext(), mBinding.getRoot().getContext().getString(R.string.record_saved), Toast.LENGTH_SHORT).show();
        }
    }
}
