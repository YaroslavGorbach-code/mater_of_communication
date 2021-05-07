package com.YaroslavGorbach.delusionalgenerator.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.core.content.res.ResourcesCompat;

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
