package com.android.hubin.themeswitcher;

import android.app.Application;

import com.android.hubin.themeswitcher.colorUi.util.SharedPreferencesMgr;


/**
 * Created by chengli on 15/6/14.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesMgr.init(this, "derson");
    }
}
