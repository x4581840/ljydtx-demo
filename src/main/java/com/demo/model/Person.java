package com.demo.model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Person implements InitializingBean, DisposableBean {

    @PostConstruct
    public void init_person() {
        System.out.println("Person的init方法");
    }

    public Person() {
        System.out.println("Person的构造方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean的destroy()方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean的 afterPropertiesSet方法");
    }
}
