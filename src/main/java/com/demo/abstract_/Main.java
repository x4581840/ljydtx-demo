package com.demo.abstract_;

public class Main {
    public static void main(String[] args) {
        HelloClient client = new HelloClient();
        client.abstract_method("hello");
        client.baseInterfaceMethod_b("world");
    }
}
