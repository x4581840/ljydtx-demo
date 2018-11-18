package com.demo.abstract_;

public class HelloClient extends AbstractClient {

    @Override
    public void abstract_method(String msg) {
        System.out.println("HelloClient-"+msg);
    }

    @Override
    public void baseInterfaceMethod_b(String msg) {
        super.baseInterfaceMethod_a("a");
    }
}
