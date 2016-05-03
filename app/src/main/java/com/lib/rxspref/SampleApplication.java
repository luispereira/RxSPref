package com.lib.rxspref;

import android.app.Application;

import com.lib.rxspreflib.RxSPref;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author lpereira on 18/01/2016.
 */
public class SampleApplication extends Application {

    private RxSPref sPref;
    private static SampleApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            LeakCanary.install(this);
        }

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
