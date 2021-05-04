package com.YaroslavGorbach.delusionalgenerator.screen.description;

import android.view.View;

import androidx.core.widget.NestedScrollView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDescriptionBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.util.List;

public class DescriptionView {
    interface Callback {
        void onUp();
        void onStartEx();
        void onNextData();
        void onPrevData();
    }

    private final FragmentDescriptionBinding mBinding;

    public DescriptionView(FragmentDescriptionBinding binding, Callback callback){
        mBinding = binding;
        binding.toolbar.setNavigationOnClickListener(v-> callback.onUp());
        binding.startEx.setOnClickListener(v -> callback.onStartEx());
        binding.chartLayout.nextData.setOnClickListener(v -> callback.onNextData());
        binding.chartLayout.prevData.setOnClickListener(v -> callback.onPrevData());

        // init show/hide button
        binding.scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY > oldScrollY) { binding.startEx.hide(); } else { binding.startEx.show(); }
        });

    }

    public void setTitle(String title){
        mBinding.toolbar.setTitle(title);
    }

    public void setImageId(int imageId){
        mBinding.image.setImageResource(imageId);
    }

    public void setDescription(String description){
        mBinding.description.setText(description);
    }

    public void setStatisticsText(Exercise.Category category){
        if (category == Exercise.Category.TONGUE_TWISTER)
            mBinding.chartLayout.statisticsText.setText(R.string.statistics_text_tt);
        if (category == Exercise.Category.VOCABULARY)
            mBinding.chartLayout.statisticsText.setText(R.string.statistics_text_vocabulary);
        if (category == Exercise.Category.SPEAKING)
            mBinding.chartLayout.statisticsText.setText(R.string.statistics_text_speaking);
    }

    public void setChartData(List<InputData> data){
        if (data.isEmpty()) showNoData();
        else mBinding.chartLayout.chart.setData(data);
    }

    public void showNoData(){
        mBinding.chartLayout.noData.setVisibility(View.VISIBLE);
        mBinding.chartLayout.chart.setVisibility(View.GONE);
    }

}
