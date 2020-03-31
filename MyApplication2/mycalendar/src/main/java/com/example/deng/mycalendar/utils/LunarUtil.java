package com.example.deng.mycalendar.utils;


import android.util.Log;

import com.date.util.Before;
import com.date.util.DateBean;
import com.date.util.Lunar;
import com.date.util.LunarSolarConverter;
import com.date.util.Shift;
import com.date.util.Solar;
import com.date.util.Swift;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LunarUtil {


    private static final String[] solarTerm = {
             "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至","小暑", "大暑", "立秋", "处暑", "白露",
             "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至","小寒", "大寒", "立春", "雨水", "惊蛰",};

    private static final String[] monthInfo = new String[]{"", "正月", "二月", "三月", "四月", "五月",
            "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};

    private static final String[] dayInfo = new String[]{"", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    // 允许输入的最小年份
    private final static int MIN_YEAR = 1900;
    // 允许输入的最大年份
    private final static int MAX_YEAR = 2100;
    // 阳历日期计算起点
    private final static String START_DATE = "19000130";

    /**
     * 阳历转阴历
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String solarToLunar(int year, int month, int day) {
        Solar solar=new Solar();
        solar.solarDay=day;
        solar.solarMonth=month;
        solar.solarYear=year;
        boolean isleap=LunarSolarConverter.SolarToLunar(solar).isleap;
        int Lyear=LunarSolarConverter.SolarToLunar(solar).lunarYear;
        int Lmonth=LunarSolarConverter.SolarToLunar(solar).lunarMonth;
        int Lday=LunarSolarConverter.SolarToLunar(solar).lunarDay;
        String result=Swift.ShiftLunar(Lyear,Lmonth,Lday,isleap);
        return result;
    }
    public static String[] solarToLunarNum(int year, int month, int day) {
        String[] shuzu=new String[5];
        Solar solar=new Solar();
        solar.solarDay=day;
        solar.solarMonth=month;
        solar.solarYear=year;
        boolean isleap=LunarSolarConverter.SolarToLunar(solar).isleap;
        int Lyear=LunarSolarConverter.SolarToLunar(solar).lunarYear;
        int Lmonth=LunarSolarConverter.SolarToLunar(solar).lunarMonth;
        int Lday=LunarSolarConverter.SolarToLunar(solar).lunarDay;
        shuzu[0]=String.valueOf(isleap);
        shuzu[1]=String.valueOf(Lyear);
        shuzu[2]=String.valueOf(Lmonth);
        shuzu[3]=String.valueOf(Lday);
        return shuzu;
    }
    /**
     * 计算两个阳历日期相差的天数。
     *
     * @param startDate 开始时间
     * @param endDate   截至时间
     * @return 天数
     */
    private static int daysBetween(Date startDate, Date endDate) {
        int days = 0;
        //将转换的两个时间对象转换成Calendar对象
        Calendar can1 = Calendar.getInstance();
        can1.setTime(startDate);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(endDate);
        //拿出两个年份
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        //天数

        Calendar can = null;
        //如果can1 < can2
        //减去小的时间在这一年已经过了的天数
        //加上大的时间已过的天数
        if (can1.before(can2)) {
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        } else {
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2 - year1); i++) {
            //获取小的时间当前年的总天数
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            //再计算下一年。
            can.add(Calendar.YEAR, 1);
        }
        return days;
    }
    public static int[] getSpringFestival(int year){
        int[] sd=new int[3];
        Lunar lunar=new Lunar();
        lunar.isleap=false;
        lunar.lunarYear=year;
        lunar.lunarMonth=1;
        lunar.lunarDay=1;
        Solar solar=LunarSolarConverter.LunarToSolar(lunar);
        sd[0]=solar.solarYear;
        sd[1]=solar.solarMonth;
        sd[2]=solar.solarDay;
        return sd;
    }
    public static String getLunarHoliday(int year, int month, int day) {


        String holiday = "";
        if(solarToLunarNum(year, month, day)[0].equals("false")) {
            if (solarToLunarNum(year, month, day)[2].equals("1") && solarToLunarNum(year, month, day)[3].equals("1")) {
                holiday = "春节";
            } else if (solarToLunarNum(year, month, day)[2].equals("1") && solarToLunarNum(year, month, day)[3].equals("15")) {
                holiday = "元宵节";
            } else if (solarToLunarNum(year, month, day)[2].equals("2") && solarToLunarNum(year, month, day)[3].equals("2")) {
                holiday = "龙抬头";
            } else if (solarToLunarNum(year, month, day)[2].equals("5")&& solarToLunarNum(year, month, day)[3].equals("5")) {
                holiday = "端午节";
            } else if (solarToLunarNum(year, month, day)[2].equals("7") && solarToLunarNum(year, month, day)[3].equals("7")) {
                holiday = "七夕节";
            } else if (solarToLunarNum(year, month, day)[2].equals("7") && solarToLunarNum(year, month, day)[3].equals("15")) {
                holiday = "中元节";
            } else if (solarToLunarNum(year, month, day)[2].equals("8") && solarToLunarNum(year, month, day)[3].equals("15")) {
                holiday = "中秋节";
            } else if (solarToLunarNum(year, month, day)[2].equals("9") && solarToLunarNum(year, month, day)[3] .equals("9")) {
                holiday = "重阳节";
            } else if (solarToLunarNum(year, month, day)[2].equals("12") && solarToLunarNum(year, month, day)[3].equals("8")) {
                holiday = "腊八节";
            } else if (solarToLunarNum(year, month, day)[2].equals("12") && solarToLunarNum(year, month, day)[3].equals("23")) {
                holiday = "小年";
            }else{
                if(Before.getBeforeOneDay(getSpringFestival(year)[0],getSpringFestival(year)[1],getSpringFestival(year)[2])[0]==year) {
                    if (Before.getBeforeOneDay(getSpringFestival(year)[0], getSpringFestival(year)[1], getSpringFestival(year)[2])[1] == month) {
                        if (Before.getBeforeOneDay(getSpringFestival(year)[0], getSpringFestival(year)[1], getSpringFestival(year)[2])[2] == day) {
                            holiday = "除夕";
                        }
                    }
                }
            }
            if(holiday.equals("")){
                holiday="";
            }
        }

        return holiday;
    }



    private static GregorianCalendar utcCal = null;

    /**
     * 根据阳历日期计算24节气
     */
    public static String getTermString(int solarYear, int solarMonth, int solarDay) {
       ArrayList<DateBean> arr=new ArrayList<DateBean>();
       String term="";
       arr=Swift(solarYear);
       for(int j=0;j<arr.size();j++){
           if(String.valueOf(solarYear).equals(arr.get(j).getYear())){
               if(String.valueOf(solarMonth+1).equals(arr.get(j).getMonth())){
                   if(String.valueOf(solarDay).equals(arr.get(j).getDate())){
                       term=solarTerm[j];
                       break;
                   }
               }
           }
       }
       return term;
    }
    public static ArrayList<DateBean> Swift(int year){
        ArrayList<DateBean>JieQi=new ArrayList<DateBean>();
        for(int i=0;i<24;i++){
            if(i<19){
                DateBean db= Shift.getSolar(year,i);
                JieQi.add(db);
            }
            if(i>=19){
                DateBean db=Shift.getSolar(year-1,i);
                JieQi.add(db);
            }
        }
        return JieQi;
    }
}
