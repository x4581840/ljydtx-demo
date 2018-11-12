package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.bo.UserBo;
import com.demo.model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBo userBo;

    @RequestMapping("/insertUser")
    public User test() {
        User user = new User();
        user.setUserName("zhangsan");
        user.setUserAge(20);
        user.setUserAddress("hangzhou");
        user.setUserPassword("123");
        user.setUserInfo(JSONObject.toJSONString(user));
        //user.setUserInfo(user.toString()); 如果不是json字符串，就好报错
        userBo.insertUser(user);
        return user;
    }

    @RequestMapping("/testjisshu")
    public User testjisshu() {
        MyThread myThread1 = new MyThread();
        Thread t1 = new Thread(myThread1);
        MyThread myThread2 = new MyThread();
        Thread t2 = new Thread(myThread2);
        MyThread myThread3 = new MyThread();
        Thread t3 = new Thread(myThread3);
        MyThread myThread4 = new MyThread();
        Thread t4 = new Thread(myThread4);
        MyThread myThread5 = new MyThread();
        Thread t5 = new Thread(myThread5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
//        userBo.testJishu();

        return userBo.selectUser(1);
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            userBo.testJishu();
        }
    }
}


