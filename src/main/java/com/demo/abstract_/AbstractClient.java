package com.demo.abstract_;

public abstract class AbstractClient implements IBaseInterface {

    @Override
    public void baseInterfaceMethod_a(String msg) {
        System.out.print("AbstractClient-baseInterfaceMethod_a-"+msg);
    }

    public abstract void abstract_method(String msg);

}
