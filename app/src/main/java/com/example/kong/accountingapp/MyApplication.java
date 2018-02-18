package com.example.kong.accountingapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Kong on 2018/2/18.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

}
