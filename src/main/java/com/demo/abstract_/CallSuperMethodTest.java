package com.demo.abstract_;

public class CallSuperMethodTest extends MyAbstract {

    @Override
    void func() {
        //测试调用父类的方法
        setState("hhh");
    }

    public static void main(String[] args) {
        CallSuperMethodTest test = new CallSuperMethodTest();
        test.func();
    }

}
