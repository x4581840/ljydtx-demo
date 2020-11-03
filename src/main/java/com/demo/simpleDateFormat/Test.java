package com.demo.simpleDateFormat;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description SimpleDateFormat有并发问题
 * @Author longjianyong
 * @Date 2019/10/30 4:12 PM
 * @Version 1.0
 **/
public class Test {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {

        //testBingFaWenTi();
        //testBingFaWenTi1();
        testBingFaWenTi2();
    }

    public static void testBingFaWenTi2() {
        List<String> dateStrList = Lists.newArrayList(
                "2018-04-01 10:00:01",
                "2018-04-02 11:00:02",
                "2018-04-03 12:00:03",
                "2018-04-04 13:00:04",
                "2018-04-05 14:00:05"
        );
        for (String str : dateStrList) {
            try {
                sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * //有并发问题 https://blog.csdn.net/weixin_38810239/article/details/79941964
     */
    public static void testBingFaWenTi1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            List<String> dateStrList = Lists.newArrayList(
                    "2018-04-01 10:00:01",
                    "2018-04-02 11:00:02",
                    "2018-04-03 12:00:03",
                    "2018-04-04 13:00:04",
                    "2018-04-05 14:00:05"
            );
            for (String str : dateStrList) {
                executorService.execute(() -> {
                    try {
                        sdf.parse(str);
                        //TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {

        } finally {
            executorService.shutdown();
        }
    }

    /**
     * //有并发问题 https://blog.csdn.net/weixin_38810239/article/details/79941964
     */
    public static void testBingFaWenTi() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            List<String> dateStrList = Lists.newArrayList(
                    "2018-04-01 10:00:01",
                    "2018-04-02 11:00:02",
                    "2018-04-03 12:00:03",
                    "2018-04-04 13:00:04",
                    "2018-04-05 14:00:05"
            );
            for (String str : dateStrList) {
                executorService.execute(() -> {
                    try {
                        simpleDateFormat.parse(str);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {

        } finally {
            executorService.shutdown();
        }
    }
}
