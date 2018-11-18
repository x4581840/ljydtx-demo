package com.demo.abstract_;

public abstract class AbstractClient implements IBaseInterface {

//    public AbstractClient() {
//        System.out.println("AbstractClient的构造方法");
//    }

    @Override
    public void baseInterfaceMethod_a(String msg) {
        System.out.print("AbstractClient-baseInterfaceMethod_a-"+msg);
    }

    public abstract void abstract_method(String msg);

}
