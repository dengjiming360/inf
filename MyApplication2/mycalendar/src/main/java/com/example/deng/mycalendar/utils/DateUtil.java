package com.example.deng.mycalendar.utils;

import android.util.Log;

import com.date.util.GetWeek;
import com.date.util.Swift;

public class DateUtil {
    public static String Date(String[] day){
        String result=Swift.ShiftLunar(Integer.valueOf(day[1]),Integer.valueOf(day[2]),Integer.valueOf(day[3]),Boolean.valueOf(day[0]));
        return result;
    }
    public static String Week(int y,int m,int d){
        String w=GetWeek.week(y,m,d);
        return w;
    }
    public static boolean isRegularHoliday(String festival){
        String[] regular={"元旦","春节","清明节","劳动节","端午节","中秋节","国庆节"};
        for(int i=0;i<regular.length;i++){
            if(festival.contains(regular[i])){
                return true;
            }
        }
        return false;
    }
}
