package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Statistics_activity extends AppCompatActivity  {
    private BarChart mChart;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statigi_activity);
        initializeComponents();
        mToolbar.setNavigationOnClickListener(v->{
            finish();
        });


        BarDataSet bardataset = new BarDataSet(Repo.getInstance(this).getEntries(1), "Минуты");



        BarData data = new BarData(Repo.getInstance(this).getLabels(1), bardataset);
        mChart.setData(data); // set the data and list of labels into chart
        mChart.setDescription("Сколько минут потрачено на упражнение в определенный день");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mChart.animateY(3000);

    }


    private void initializeComponents(){
        mChart = findViewById(R.id.chart);
        mToolbar = findViewById(R.id.toolbar_statistics);
    }


}
