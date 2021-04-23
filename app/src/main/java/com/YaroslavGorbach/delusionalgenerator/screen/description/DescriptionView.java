package com.YaroslavGorbach.delusionalgenerator.screen.description;

import androidx.core.widget.NestedScrollView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
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
        binding.nextData.setOnClickListener(v -> callback.onNextData());
        binding.prevData.setOnClickListener(v -> callback.onPrevData());

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

    public void setStatisticsText(ExModel.Category category){
        if (category == ExModel.Category.TONGUE_TWISTER)
            mBinding.statisticsText.setText(R.string.statistics_text_tt);
    }

    public void setChartData(List<InputData> data){
        mBinding.chart.setData(data);
    }

}
