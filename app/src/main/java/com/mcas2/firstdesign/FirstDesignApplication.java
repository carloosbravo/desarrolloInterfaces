package com.mcas2.firstdesign;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class FirstDesignApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
