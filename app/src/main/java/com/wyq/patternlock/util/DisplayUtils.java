package com.wyq.patternlock.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;

/**
 * 界面转换尺寸工具类
 * Created by jerry on 2016/12/6.
 */
public class DisplayUtils {

    /**
     * 屏幕宽度（像素）
     */
    public static int SCREEN_WIDTH;
    /**
     * 屏幕高度（像素）
     */
    public static int SCREEN_HEIGHT;
    /**
     * 屏幕密度
     */
    public static float DENSITY;


    /**
     * 转换px为dip
     */
    public static int convertDIP2PX(Context context, float dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * 转换px为dip
     */
    public static int convertPX2DIP(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    /**
     * 转换px为sp
     */
    public static int convertPx2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 转换sp为px
     */
    public static int convertSp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕分辨率和密度
     */
    public static void getDisplayMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int rotation = wm.getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        SCREEN_WIDTH = rotation == Surface.ROTATION_0 ? displayMetrics.widthPixels : displayMetrics.heightPixels;
        SCREEN_HEIGHT = rotation == Surface.ROTATION_0 ? displayMetrics.heightPixels : displayMetrics.widthPixels;
        DENSITY = displayMetrics.density;
    }

}