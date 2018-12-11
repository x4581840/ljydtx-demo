package com.demo.abstract_;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
//        HelloClient client = new HelloClient("msg");
//        client.abstract_method("hello");
//        client.baseInterfaceMethod_b("world");

        Method m =MyAbstract.class.getDeclaredMethods()[0];
        System.out.println(m.getName()+", "+m.getModifiers());
    }
}
