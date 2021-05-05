package com.YaroslavGorbach.delusionalgenerator.screen.description;
import android.graphics.Color;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.ChartInputData;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentDescriptionBinding;
import com.YaroslavGorbach.delusionalgenerator.util.ColorUtils;

import im.dacer.androidcharts.LineView;

public class DescriptionView {
    public interface Callback {
        void onUp();
        void onStartEx();
        void onNextData();
        void onPrevData();
    }

    private final FragmentDescriptionBinding mBinding;

    public DescriptionView(FragmentDescriptionBinding binding, Callback callback) {
        mBinding = binding;
        binding.toolbar.setNavigationOnClickListener(v -> callback.onUp());
        binding.startEx.setOnClickListener(v -> callback.onStartEx());
        binding.chartLayout.nextData.setOnClickListener(v -> callback.onNextData());
        binding.chartLayout.prevData.setOnClickListener(v -> callback.onPrevData());

        // init show/hide button
        binding.scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY > oldScrollY) {
                        binding.startEx.hide();
                    } else {
                        binding.startEx.show();
                    }
                });

    }

    public void setTitle(String title) {
        mBinding.toolbar.setTitle(title);
    }

    public void setImageId(int imageId) {
        mBinding.image.setImageResource(imageId);
    }

    public void setDescription(String description) {
        mBinding.description.setText(description);
    }

    public void setStatisticsText(Exercise.Category category) {
        if (category == Exercise.Category.TONGUE_TWISTER)
            mBinding.chartLayout.statisticsText.setText(R.string.statistics_text_tt);
        if (category == Exercise.Category.VOCABULARY)
            mBinding.chartLayout.statisticsText.setText(R.string.statistics_text_vocabulary);
        if (category == Exercise.Category.SPEAKING)
            mBinding.chartLayout.statisticsText.setText(R.string.statistics_text_speaking);
    }

    public void setChartData(ChartInputData chartData) {
        if (chartData.isEmpty()){
            showNoData();
        } else {
            mBinding.chartLayout.chart.setDrawDotLine(false);
            mBinding.chartLayout.chart.setShowPopup(LineView.SHOW_POPUPS_All);
            mBinding.chartLayout.chart.setBottomTextList(chartData.getLabels());
            mBinding.chartLayout.chart.setColorArray(new int[]{ColorUtils.getColorPrimary(mBinding.getRoot().getContext()), Color.GREEN, Color.GRAY, Color.CYAN});
            mBinding.chartLayout.chart.setDataList(chartData.getData());
        }
    }

    private void showNoData() {
        mBinding.chartLayout.noData.setVisibility(View.VISIBLE);
        mBinding.chartLayout.chart.setVisibility(View.GONE);
    }
}
