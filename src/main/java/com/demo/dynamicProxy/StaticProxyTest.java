package com.demo.dynamicProxy;

public class StaticProxyTest {
    public static void main(String[] args) {

        //被代理的学生张三，他的班费上交有代理对象monitor（班长）完成
        Person1 zhangsan = new Student("张三");

        //生成代理对象，并将张三传给代理对象
        Person1 monitor = new StudentsProxy(zhangsan);

        //班长代理上交班费
        monitor.giveMoney();
    }
}
