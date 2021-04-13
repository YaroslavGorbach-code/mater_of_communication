package com.YaroslavGorbach.delusionalgenerator.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class Permissions {
    private static final String recordPermission = Manifest.permission.RECORD_AUDIO;
    private static final int PERMISSION_CODE = 21;

    public static boolean checkRecordPermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, recordPermission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }
}
