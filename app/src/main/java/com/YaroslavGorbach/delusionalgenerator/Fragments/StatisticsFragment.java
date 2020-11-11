package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogClearStatistics;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


public class StatisticsFragment extends Fragment {

    private BarChart mChartMinutes;
    private BarChart mChartWorldCount;
    private CardView mChartMinutes_cv;
    private CardView mChartWords_cv;
    private Toolbar mToolbar;
    int mIdEx;

    public StatisticsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static StatisticsFragment newInstance() {

        return new StatisticsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        mChartMinutes = view.findViewById(R.id.chartMinutes);
        mChartWorldCount = view.findViewById(R.id.chartWorlds);
        mToolbar = view.findViewById(R.id.toolbar_statistics);
        mToolbar.inflateMenu(R.menu.menu_statistic);
        mChartMinutes_cv = view.findViewById(R.id.cardView2);
        mChartWords_cv = view.findViewById(R.id.cardView1);
        mIdEx = StatisticsFragmentArgs.fromBundle(getArguments()).getExId();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*Потписка на слушателей*/
        createTimeChart();
        createWorldCounterChart();
        Repo.getInstance(getContext()).addListener(this::createTimeChart);
        Repo.getInstance(getContext()).addListener(this::createWorldCounterChart);

        /*Показ диалога который для удаления статистики*/
        mToolbar.setOnMenuItemClickListener(v->{
            DialogClearStatistics.crete(mIdEx).show(getChildFragmentManager(),null);
            return true;
        });

        /*Если статисти для упражнений второй категории то убрать чарт с минутами*/
        switch (mIdEx){
            case 20:
            case 21:
            case 22:
                mChartMinutes_cv.setVisibility(View.GONE);
                break;

        }
        /*Оброботка нажатия на стрелку назад*/
        mToolbar.setNavigationOnClickListener(v->{
            NavDirections action = StatisticsFragmentDirections.
                    actionStatisticsFragmentToExercisesDescriptionFragment().setExId(mIdEx);
            Navigation.findNavController(view).navigate(action);
        });


    }

    /*Создаем чарт для времени*/
    private  void createTimeChart(){
        BarDataSet bardataset = new BarDataSet(Repo.getInstance(getContext()).getEntriesTime(mIdEx), "Минуты");
        BarData data = new BarData(Repo.getInstance(getContext()).getTimeLabels(mIdEx), bardataset);
        mChartMinutes.setData(data); // set the data and list of labels into chart
        mChartMinutes.setDescription("Количество минут потраченых на сессию");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mChartMinutes.animateY(2000);
    }

    /*Создаем чарт для количества слов*/
    private  void createWorldCounterChart(){
        BarDataSet bardataset = new BarDataSet(Repo.getInstance(getContext()).getEntriesWorldCount(mIdEx), "Слова");
        BarData data = new BarData(Repo.getInstance(getContext()).getWorldCountLabels(mIdEx), bardataset);
        mChartWorldCount.setData(data); // set the data and list of labels into chart
        mChartWorldCount.setDescription("Количество пройденых слов за сессию");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mChartWorldCount.animateY(2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Repo.getInstance(getContext()).removeListener(this::createTimeChart);
        Repo.getInstance(getContext()).removeListener(this::createWorldCounterChart);
    }
}