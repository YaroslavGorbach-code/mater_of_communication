package com.YaroslavGorbach.delusionalgenerator.Activityes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogClearStatistics;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class Statistics_activity extends AppCompatActivity  {
    private BarChart mChartMinutes;
    private BarChart mChartWorldCount;
    private Toolbar mToolbar;
    int mIdEx;
    public static final String EXTRA_ID_EX = "EXTRA_ID_EX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_activity);
        initializeComponents();

        mToolbar.setNavigationOnClickListener(v->{
            finish();
        });

        mToolbar.setOnMenuItemClickListener(v->{
            DialogClearStatistics.crete(mIdEx).show(getSupportFragmentManager(),null);
            return true;

        });

        createTimeChart();
        createWorldCounterChart();
        Repo.getInstance(this).addListener(this::createTimeChart);
        Repo.getInstance(this).addListener(this::createWorldCounterChart);

    }


    private void initializeComponents(){
        mChartMinutes = findViewById(R.id.chartMinutes);
        mChartWorldCount = findViewById(R.id.chartWorlds);
        mToolbar = findViewById(R.id.toolbar_statistics);
        mToolbar.inflateMenu(R.menu.menu_statistic);
        mIdEx = getIntent().getIntExtra(EXTRA_ID_EX,-1);
    }

    private  void createTimeChart(){
        BarDataSet bardataset = new BarDataSet(Repo.getInstance(this).getEntriesTime(mIdEx), "Минуты");
        BarData data = new BarData(Repo.getInstance(this).getTimeLabels(mIdEx), bardataset);
        mChartMinutes.setData(data); // set the data and list of labels into chart
        mChartMinutes.setDescription("Количество потраченого времени на упражнение");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mChartMinutes.animateY(2000);
    }

    private  void createWorldCounterChart(){
        BarDataSet bardataset = new BarDataSet(Repo.getInstance(this).getEntriesWorldCount(mIdEx), "Слова");
        BarData data = new BarData(Repo.getInstance(this).getWorldCountLabels(mIdEx), bardataset);
        mChartWorldCount.setData(data); // set the data and list of labels into chart
        mChartWorldCount.setDescription("Количество пройденых слов в определенный день");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mChartWorldCount.animateY(2000);
    }

    private void setTheme(){
        String color = Repo.getInstance(Statistics_activity.this).getThemeState();
        switch (color){

            case "blue":

                setTheme(R.style.AppTheme_blue);

                break;

            case "green":

                setTheme(R.style.AppTheme_green);
                break;

            case "orange":

                setTheme(R.style.AppTheme_orange);
                break;

            case "red":

                setTheme(R.style.AppTheme_red);
                break;

            case "purple":

                setTheme(R.style.AppTheme_purple);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        Repo.getInstance(this).removeListener(this::createTimeChart);
        Repo.getInstance(this).removeListener(this::createWorldCounterChart);
        super.onDestroy();
    }
}
