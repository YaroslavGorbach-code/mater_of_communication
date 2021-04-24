package com.YaroslavGorbach.delusionalgenerator.component.description;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;
import java.util.List;

public interface Description {
    int getDescriptionId();
    int getImageId();
    Exercise.Category getCategory();
    LiveData<List<InputData>> getStatistics();
    void onStatisticsNext();
    void onStatisticsPrevious();

}
