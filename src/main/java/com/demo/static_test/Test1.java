package com.demo.static_test;

public class Test1 {

    public static void test1() {
        System.out.println("1111");
    }

    public void test2() {
        System.out.println("1111");
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        t.test1();
        t.test2();
        Test1.test1();
    }
}
