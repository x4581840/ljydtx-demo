package com.demo.util;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest extends Object{
    public static void main(String[] args) throws Throwable {
        test_1();
    }

    public static void test_1() throws Throwable {
        Map map = new HashMap(8);
        map.put("aa","bb");

        //进行put操作时，如果key是重复的，覆盖完在返回旧的值（1.7),返回新的值（1.8)
        //Object obj = map.put("aa","cc");
        //System.out.println(obj);//bb

        System.out.println(map.size());//1  元素的数量

        map.put("dd","ee");
        System.out.println(map.size());//2  元素的数量

        map.put("ee","ff");
        map.put("ff","gg");
        map.put("gg","hh");
        map.put("hh","ii");
        map.put("ii","jj");

        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.finalize();
    }
}
