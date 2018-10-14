package com.demo.util_concurrent.atomic.concurrentHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    private Map<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Executor executor = new Executor();
        executor.test();
    }
}
