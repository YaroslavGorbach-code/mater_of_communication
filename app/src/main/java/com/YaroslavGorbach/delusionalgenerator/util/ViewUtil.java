package com.YaroslavGorbach.delusionalgenerator.util;

public class ViewUtil {
    public static int getCorrectTextSize(int length){
    if (length < 20){
            return 50;
        }else {
            return 30;
        }
    }
}
