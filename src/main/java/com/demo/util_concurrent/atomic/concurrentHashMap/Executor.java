package com.demo.util_concurrent.atomic.concurrentHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Executor {
    private Map<String, Integer> map = new ConcurrentHashMap<>();
    //Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());

    public void test() {

        //map.put("key", 1);
        int counter = 0;
        synchronized (map) {
            if(map.containsKey("key")) {
                counter = map.get("key");
            }
            map.put("key", counter++);
        }
        System.out.println(map.get("key"));
        System.out.println(counter);
    }
}
