package com.jacky.airline.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * 2018/3/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class AndroidUtils {
    public static boolean requestPermission(Activity activity, String permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23 && activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{permission}, requestCode);
            return false;
        }

        return true;
    }
}
