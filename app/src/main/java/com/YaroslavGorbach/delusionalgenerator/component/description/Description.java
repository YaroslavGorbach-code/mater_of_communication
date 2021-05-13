package com.YaroslavGorbach.delusionalgenerator.component.description;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.domain.ChartInputData;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;

public interface Description {
    int getDescriptionId();
    int getImageId();
    Exercise.Category getCategory();
    LiveData<ChartInputData> getChartData();
    void onChartNext();
    void onChartBack();

}
