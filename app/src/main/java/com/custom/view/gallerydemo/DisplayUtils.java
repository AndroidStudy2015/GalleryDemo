package com.custom.view.gallerydemo;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by apple on 2017/12/30.
 */

public class DisplayUtils {

    public static int getScreenWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
