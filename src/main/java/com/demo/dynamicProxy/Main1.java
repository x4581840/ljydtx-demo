package com.demo.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Java自带的动态代理实现
 */
public class Main1 {
    public static void main(String[] args) {
        //要代理的真实的对象
        UserService userService = new UserServiceImpl();
        //生成代理类
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);
        //下面两种效果都一样,think in java 是第一种
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                            userService.getClass().getInterfaces(), invocationHandler);
        /*UserService userServiceProxy = (UserService) Proxy.newProxyInstance(invocationHandler.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), invocationHandler);*/

        System.out.println(userServiceProxy.getClass().getName());
        System.out.println(userServiceProxy.getName(1));
        System.out.println(userServiceProxy.getAge(1));

        System.out.println("=================");
        System.out.println(userServiceProxy);
    }
}
