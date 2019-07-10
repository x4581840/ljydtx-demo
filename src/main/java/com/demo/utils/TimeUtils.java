package com.demo.utils;

import java.util.Calendar;
import java.util.Date;
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
}
