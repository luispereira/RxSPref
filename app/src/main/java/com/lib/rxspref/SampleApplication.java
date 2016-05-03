package com.lib.rxspref;

import android.app.Application;

import com.lib.rxspreflib.RxSPref;

/**
 * @author lpereira on 18/01/2016.
 */
public class SampleApplication extends Application {

    private RxSPref sPref;
    private static SampleApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sPref = RxSPref.init(this).buildSettings();
        sInstance = this;
    }

    public static SampleApplication getInstance() {
        return sInstance;
    }

    public RxSPref getPref() {
        return sPref;
    }
}
