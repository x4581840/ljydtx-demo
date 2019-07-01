package com.demo.stringutils;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsDemo {
    public static void main(String[] args) {
        test_join();
    }

    private static void test_join() {
        String[] str = {"1", "2", "3", "4"};
        long start = System.currentTimeMillis();
        String str2 = StringUtils.join(str, "|");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(str2);
        //结果： 1|2|3|4

        long start1 = System.currentTimeMillis();
        String str3 = "1" + "|" + "2" + "|" + "3" + "|" + "4";
        System.out.println(System.currentTimeMillis() - start1);
        System.out.println(str3);

        String[] strArray = new String[1000];
        for (int i = 0; i < 1000; i++) {
            strArray[i] = String.valueOf(i);
        }

        System.out.println("array size:" + strArray.length);

        long start2 = System.currentTimeMillis();
        String str4 = StringUtils.join(strArray, "|");
        System.out.println(System.currentTimeMillis() - start2);
        System.out.println(str4);

        long begin = System.currentTimeMillis();
        String res = "";
        for (int i = 0; i < 1000; i++) {
            res += "|" + String.valueOf(i);
        }
        System.out.println("cost:" + (System.currentTimeMillis() - begin));
        System.out.println(res);

    }
}
