package com.demo.spring.annotation.autowried_resource.impl;

import com.demo.spring.annotation.autowried_resource.IHuman;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/4/28 3:22 PM
 * @Version 1.0
 **/

@Component("whiteMan")
public class WhiteMan implements IHuman {

    @Override
    public void printMessage(String name) {
        System.out.println("我是白人，我的名字叫做：" + name);
    }
}
