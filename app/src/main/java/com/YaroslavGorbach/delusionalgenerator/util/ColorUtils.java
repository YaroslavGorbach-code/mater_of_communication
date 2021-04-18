package com.YaroslavGorbach.delusionalgenerator.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;

import com.YaroslavGorbach.delusionalgenerator.R;

public class ColorUtils {

   public static int getColorPrimary(Context context){
       TypedValue typedValue = new TypedValue();
       TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorPrimary });
       int color = a.getColor(0, 0);
       a.recycle();
       return color;
    }
}
