package com.demo.zhujie_setvalue;

import org.springframework.stereotype.Service;

@Service
public class SomeService {

//    @FromRedis(key = "yourKey")
    public void oneMethod(@FromRedis(key = "yourKey") String valueFromRedis, String lastParm) {
        // 在这里，valueFromRedis就是从Redis中key为'yourKey'取出的值
        System.out.println("oneMethod Value from Redis: " + valueFromRedis + ",lastParm: " + lastParm);
    }

    public void twoMethod(@FromRedis(key = "yourKey") String valueFromRedis, String lastParm) {
        // 在这里，valueFromRedis就是从Redis中key为'yourKey'取出的值
        System.out.println("twoMethod Value from Redis: " + valueFromRedis + ",lastParm: " + lastParm);
    }
}

