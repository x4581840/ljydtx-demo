package com.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/27 4:16 PM
 * @Version 1.0
 **/
public class TimeUtils {
    public static Date getCurrentUTCDate() {
        //转换为当前时间为UTC时间
        return new Date();
    }

    //舍去日期中的时分秒
    public static Date ceilDateToDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 将时分秒,毫秒域清零
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 转换本地时间为UTC的相同时间，如东八区八时转化为零时区八时
     *
     * @param date 需要转换的时间
     * @return
     */
    public static Date fillToUTC(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, TimeZone.getDefault().getRawOffset());
        return calendar.getTime();
    }

    /**
     * get start date of given week no of a year
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static String getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        Date date = cal.getTime();
        System.out.println(date.getHours());
        System.out.println(date.getMinutes());
        System.out.println(date.getSeconds());
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * get the end day of given week no of a year.
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static String getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * get Calendar of given year
     *
     * @param year
     * @return
     */
    private static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //设置当前时间是周一
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    public static void main(String[] args) {
        System.out.println("开始时间: " + TimeUtils.getStartDayOfWeekNo(2019, 12));
        System.out.println("结束时间:" + TimeUtils.getEndDayOfWeekNo(2019, 12));

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); //一周中的第几天  结果是4 其实是3，需要减1
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH)); //一个月中的第几天 结果是10

        System.out.println(calendar.getFirstDayOfWeek());
        System.out.println(calendar.getFirstDayOfWeek() == Calendar.SUNDAY);

        calendar.set(Calendar.MONTH, Calendar.FEBRUARY); //2月

//        calendar.set(Calendar.YEAR, 2019);
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        calendar.set(Calendar.WEEK_OF_YEAR, 2);
//
//        System.out.println("年："+calendar.get(Calendar.YEAR));
//        System.out.println("月："+(calendar.get(Calendar.MONTH)+1));
//        System.out.println("日："+calendar.get(Calendar.DAY_OF_MONTH));

//        cal();

    }

    private static void cal() {
        int year = 2019;
        int week = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        int dayOfWeek = 7 - calendar.get(Calendar.DAY_OF_WEEK) + 1;//算出第一周还剩几天 +1是因为1号是1天
        week = week - 2;//周数减去第一周再减去要得到的周
        calendar.add(Calendar.DAY_OF_YEAR, week * 7 + dayOfWeek);
        System.out.println("开始日期：" + new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        System.out.println("结束日期：" + new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
    }
}
