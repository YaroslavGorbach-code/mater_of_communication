package com.YaroslavGorbach.delusionalgenerator.util;

import android.content.Context;
import android.util.TypedValue;

import com.YaroslavGorbach.delusionalgenerator.R;

public class getThemeColor {
    public static int getAccentColor(final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme ().resolveAttribute (R.attr.colorAccent, value, true);
        return value.data;
    }

    public static int getBackgroundColor(final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme ().resolveAttribute (R.attr.colorPrimary, value, true);
        return value.data;
    }
}
