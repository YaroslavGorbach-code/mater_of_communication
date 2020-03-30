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
    int mIdEx;
    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statigi_activity);
        initializeComponents();

        mToolbar.setNavigationOnClickListener(v->{
            finish();
        });

        mToolbar.setOnMenuItemClickListener(v->{
            DialogClearStatistics.crete(mIdEx).show(getSupportFragmentManager(),null);
            return true;

        });

        createChart();
        Repo.getInstance(this).addListener(this::createChart);
        
    }


    private void initializeComponents(){
        mChart = findViewById(R.id.chart);
        mToolbar = findViewById(R.id.toolbar_statistics);
        mToolbar.inflateMenu(R.menu.menu_statistic);
        mIdEx = getIntent().getIntExtra(EXTRA_ID_EX,-1);
    }

private  void createChart(){
    BarDataSet bardataset = new BarDataSet(Repo.getInstance(this).getEntries(mIdEx), "Минуты");
    BarData data = new BarData(Repo.getInstance(this).getLabels(mIdEx), bardataset);
    mChart.setData(data); // set the data and list of labels into chart
    mChart.setDescription("Сколько минут потрачено на упражнение в определенный день");  // set the description
    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
    mChart.animateY(2000);
}

}
