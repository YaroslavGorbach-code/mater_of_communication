package com.YaroslavGorbach.delusionalgenerator.component.description;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.screen.chartView.data.InputData;

import java.util.List;

public interface Description {
    int getDescriptionId();
    int getImageId();
    ExModel.Name getExName();
    ExModel.Category getCategory();
    List<InputData> getStatisticsLast();
    List<InputData> getStatisticsNext();
    List<InputData> getStatisticsPrevious();

}
