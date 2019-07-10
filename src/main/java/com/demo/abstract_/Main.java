package com.demo.abstract_;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
//        HelloClient httpclient = new HelloClient("msg");
//        httpclient.abstract_method("hello");
//        httpclient.baseInterfaceMethod_b("world");

        Method m = MyAbstract.class.getDeclaredMethods()[0];
        System.out.println(m.getName() + ", " + m.getModifiers());
    }
}
