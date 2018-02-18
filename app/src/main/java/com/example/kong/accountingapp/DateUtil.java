package com.example.kong.accountingapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kong on 2018/2/18.
 */

public class DateUtil {

    // unix time -> 11:11
    public static String getFormattedTime(long timeStamp){
        // yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(timeStamp));
    }

    // 2017-06_15
    public static String getFormattedDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

}
