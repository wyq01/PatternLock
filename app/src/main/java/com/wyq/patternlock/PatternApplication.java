package com.wyq.patternlock;

import android.app.Application;

import com.wyq.patternlock.util.DisplayUtils;

/**
 * Created by Joe.Wang on 2017/1/4.
 */
public class PatternApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayUtils.getDisplayMetrics(this);
    }

}