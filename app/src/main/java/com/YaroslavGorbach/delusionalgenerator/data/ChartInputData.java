package com.YaroslavGorbach.delusionalgenerator.data;

import java.util.ArrayList;

public class ChartInputData {
    private final ArrayList<ArrayList<Integer>> chartData = new ArrayList<>();
    private final ArrayList<Integer> data = new ArrayList<>();
    private final ArrayList<Long> time = new ArrayList<>();
    private  ArrayList<String> labels = new ArrayList<>();


    public ArrayList<ArrayList<Integer>> getData() {
        chartData.clear();
        chartData.add(data);
        return chartData;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public ArrayList<Long> getTime() {
        return time;
    }

    public void addLabel(String label) {
        labels.add(label);
    }

    public void addValue(int value){
        data.add(value);
    }

    public void addTime(long t){
        time.add(t);
    }

    public boolean isEmpty(){
        return data.isEmpty() || time.isEmpty();
    }
}
