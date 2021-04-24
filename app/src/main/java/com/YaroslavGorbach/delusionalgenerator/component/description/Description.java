package com.YaroslavGorbach.delusionalgenerator.component.description;
import androidx.lifecycle.LiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;
import java.util.List;

public interface Description {
    int getDescriptionId();
    int getImageId();
    ExModel.Category getCategory();
    LiveData<List<InputData>> getStatistics();
    void onStatisticsNext();
    void onStatisticsPrevious();

}
