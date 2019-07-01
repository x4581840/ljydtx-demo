package com.demo.springboot_test.filter;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/7 11:14 AM
 * @Version 1.0
 **/

@Component
public class UserDao {

    public void printMsg(String message) {
        System.out.println("msg:" + message);
    }
}
