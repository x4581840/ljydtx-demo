package com.demo.string;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019-06-25 14:31
 * @Version 1.0
 **/

public class StringDemo {
    public static void main(String[] args) {
//        test_01();
        test_02();
    }

    /**
     * 答案是false,false,true。背后的原理是：
     * 1、String str1 = "abc";通过字面量的方式创建，abc存储于字符串常量池中；
     * 2、String str2 = new String("abc");通过new对象的方式创建字符串对象，引用地址存放在堆内存中，abc则存放在字符串常量池中；所以str1 == str2?显然是false
     * 3、String str3 = str2.intern();由于str2调用了intern()方法，会返回常量池中的数据，地址直接指向常量池，所以str1 == str3；
     * 而str2和str3地址值不等所以也是false（str2指向堆空间，str3直接指向字符串常量池）。
     */
    private static void test_01() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str1 == str3);
    }

    private static void test_02() {
        String str1 = "计算机";
        String str2 = "计算机";
        System.out.println("str1==str2:" + (str1 == str2)); // true

        String str3 = new String("计算机");
        System.out.println("str1==str3:" + (str1 == str3)); // false
        System.out.println("str1=" + str1 + ",str3.intern()=" + str3.intern());
        System.out.println("str1==str3.intern():" + (str1 == str3.intern())); // true
        System.out.println("str2==str3.intern():" + (str2 == str3.intern())); // true

        String str4 = new String("计算机");
        System.out.println("str3=" + str3 + ",str4=" + str4);
        System.out.println("str3==str4:" + (str3 == str4)); // false
        System.out.println("str3.intern()==str4.intern():" + (str3.intern() == str4.intern())); // true


        String str5 = new StringBuilder("软件").append("工程").toString();
        System.out.println("str5.intern() == str5:" + (str5.intern() == str5)); // true

        String str6 = new String(new StringBuilder("物联网").append("工程").toString());
        System.out.println("str6.intern() == str6:" + (str6.intern() == str6)); // true

        String str7 = new String("物联网");
        System.out.println("str7.intern() == str7:" + (str7.intern() == str7)); // false
    }
}

