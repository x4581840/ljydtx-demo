package com.demo.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest extends Object{
    public static void main(String[] args) throws Throwable {
//        test_1();
        test_getOrDefault();
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

    //getOrDefault(key, default)
    //如果key存在于map，或者通过key获取到的对象result不为null，则返回结果等于这个result，否则返回结果为default
    public static void test_getOrDefault() {

        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        map.put("c", null);
        map.put("d", "D");

        String res = "";
        // old
        if(map.containsKey("c")) {
            String value = map.get("c");
            if(StringUtils.isBlank(value)) {
                value = "default";
                res =  value;
            }
        }
        System.out.println("res = "+res);

        String res1 = map.getOrDefault("c", "default1");
        System.out.println("res1 = "+res1); //null 因为存在key为c

        String res2 = map.getOrDefault("d", "default2");
        System.out.println("res2 = "+res2); //D 因为get("d")不为空

        String res3 = map.getOrDefault("e", "default3");
        System.out.println("res3 = "+res3); //defaul 不存在key


    }
}
