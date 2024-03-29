package com.android.luomeiji_rider;

import android.app.Application;

import com.android.luomeiji_rider.tools.UiDensity;
import com.vondear.rxtool.RxTool;

public class RiderAppliction extends Application {

    public static RiderAppliction app;

    @Override
    public void onCreate() {
        super.onCreate();
        UiDensity.setDensity(this);
        app = this;
        RxTool.init(this);
    }

    public static RiderAppliction getApp() {
        return app;
    }
}
