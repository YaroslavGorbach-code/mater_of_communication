package com.YaroslavGorbach.delusionalgenerator.component.description;
import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ChartInputData;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import java.util.ArrayList;

public interface Description {
    int getDescriptionId();
    int getImageId();
    Exercise.Category getCategory();
    LiveData<ChartInputData> getChartData();
    void onStatisticsNext();
    void onStatisticsPrevious();

}
