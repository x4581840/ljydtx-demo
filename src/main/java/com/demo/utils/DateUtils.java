package com.demo.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/7/15 10:40 AM
 * @Version 1.0
 **/
public class DateUtils {

    /**
     * get start date of given week no of a year
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static Date getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        Date date = cal.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        return cal.getTime();
    }

    /**
     * get the end day of given week no of a year.
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static Date getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date date = cal.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        return cal.getTime();
    }

    /**
     * get Calendar of given year
     *
     * @param year
     * @return
     */
    private static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //设置calendar为周一
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    public static void main(String[] args) {
        System.out.println("开始时间: " + DateUtils.getStartDayOfWeekNo(2019, 2));
        System.out.println("结束时间:" + DateUtils.getEndDayOfWeekNo(2019, 2));
    }
}
