package com.lib.rxspref;

import android.app.Application;

import com.lib.rxspreflib.RxSPref;

/**
 * @author lpereira on 18/01/2016.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxSPref.init(this);
    }
}
