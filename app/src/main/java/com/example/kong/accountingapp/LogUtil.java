package com.example.kong.accountingapp;

import android.util.Log;

/**
 * Created by Kong on 2018/2/18.
 */

public class LogUtil {

    private static boolean isShowLog = true;

    public LogUtil() {
    }

    public static void d(String TAG, String msg) {
        if (isShowLog) {
            Log.d(TAG, msg);
        }
    }

    public static void println(String msg){
        System.out.println(msg);
    }

}
