package com.demo.java8.interfacetest;

import com.demo.controller.UserCond;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/9/19 3:43 PM
 * @Version 1.0
 **/
public class FunctionTest {

    public static List<String> videoListByUserVoted() {
        List<UserVotedVideo> userVotedVideos = new ArrayList<>();
        UserVotedVideo uvv = new UserVotedVideo(1, "videoUUID", "2019-09-23");
        uvv = new UserVotedVideo(1, "videoUUID", "2019-09-23");
        userVotedVideos.add(uvv);
        uvv = new UserVotedVideo(2, "videoUUID2", "2019-09-23");
        userVotedVideos.add(uvv);
        uvv = new UserVotedVideo(3, "videoUUID3", "2019-09-23");
        userVotedVideos.add(uvv);
        uvv = new UserVotedVideo(4, "videoUUID3", "2019-09-23");
        userVotedVideos.add(uvv);
        uvv = new UserVotedVideo(5, "videoUUID3", "2019-09-23");
        userVotedVideos.add(uvv);
        uvv = new UserVotedVideo(6, "videoUUID3", "2019-09-23");
        userVotedVideos.add(uvv);
        if (!Objects.isNull(userVotedVideos)) {
            List<UserVotedVideo> userVotedVideos1 = userVotedVideos.stream().filter(distinctByKey(UserVotedVideo::getVideoUUID)).collect(Collectors.toList());
            return userVotedVideos1.stream().map(UserVotedVideo::getVideoUUID).collect(Collectors.toList());
        }
        return null;
    }


    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        //Predicate<T> p = o -> keyExtractor.apply(o) == null;
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        Predicate<T> p = object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
        //return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
        return p;
    }

    private static ScheduledThreadPoolExecutor executorService =
            new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    public static void main(String[] args) {
//        System.out.println(videoListByUserVoted());
//        long a = 1569232759 * 1000;
//        long b = 1569232759;
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms").format(new Date(b)));
//
//        Validate.isTrue(2 >= 0, "显示投票不能为负数");
//
//        Integer i = 1;
//        System.out.println(1>=0);

//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("lalalalala===");
//            }
//        }, 0, 3, TimeUnit.SECONDS);
//
//        /*try {
//            executorService.awaitTermination(2, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }*/
//
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hahahahaha===");
//            }
//        }, 0, 3, TimeUnit.SECONDS);
//
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        System.out.println(executorService.isTerminating());
//
//        while(true) {
//
//        }
        UserCond u = new UserCond();
        u.setId(1);

        UserCond u1 = new UserCond();

        BeanUtils.copyProperties(u, u1);
        System.out.println(u1.getId() + u.getName());

    }
}
