package com.demo.httpclient.threadPool;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/12/20 3:02 PM
 * @Version 1.0
 **/
@Component
public class HttpClientPoolTest {

    @PostConstruct
    public void testPostContruct() {
        System.out.println("postconstruct====");
    }

    public HttpClientPoolTest() {
        System.out.println("构造函数");
    }



}
