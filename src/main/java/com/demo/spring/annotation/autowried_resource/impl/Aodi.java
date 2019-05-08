package com.demo.spring.annotation.autowried_resource.impl;

import com.demo.spring.annotation.autowried_resource.ICar;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/4/28 4:16 PM
 * @Version 1.0
 **/

public class Aodi implements ICar {

    @Override
    public void printMessage(String name) {
        System.out.println("我是奥迪，我的名字是："+name);
    }
}
