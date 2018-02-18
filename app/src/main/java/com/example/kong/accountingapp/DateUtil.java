package com.example.kong.accountingapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kong on 2018/2/18.
 */

public class DateUtil {

    // unix time -> 11:11
    public static String getFormattedTime(long timeStamp) {
        // yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(timeStamp));
    }

    // 2017-06_15
    public static String getFormattedDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    private static Date strToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getWeekDay(String date) {
        String[] weekdas = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekdas[index];
    }

    public static String getDateTitle(String date){
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int monthIndex = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return months[monthIndex] + " " + String.valueOf(day); //2017-06-15 -> June 15
    }

}
