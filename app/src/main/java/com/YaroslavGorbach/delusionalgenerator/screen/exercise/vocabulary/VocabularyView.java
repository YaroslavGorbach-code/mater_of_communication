package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import com.YaroslavGorbach.delusionalgenerator.AdManager;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentVocabularyBinding;

public class VocabularyView {

    interface Callback{
        void onUp();
        void onClick();
    }

    private final FragmentVocabularyBinding mBinding;

    public VocabularyView(FragmentVocabularyBinding binding, Callback callback){
        // show Ad
        new AdManager().showBanner(binding.getRoot().getContext(), binding.bannerContainer);

        mBinding = binding;
        binding.clickArea.setOnClickListener(v -> callback.onClick());
        binding.toolbar.setNavigationOnClickListener(v -> callback.onUp());
    }

    public void setTimerValue(String value){
        mBinding.timer.setText(value);
    }

    public void setTitle(String title){
        mBinding.toolbar.setTitle(title);
    }

    public void setClickCount(String count) {
        mBinding.wordsCount.setText(count);
    }

    public void setShortDesc(String string) {
        mBinding.shortDesc.setText(string);
    }

    public void onTimerFinish(){
        mBinding.clickArea.setClickable(false);
    }
}
