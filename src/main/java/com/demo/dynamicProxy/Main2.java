package com.demo.dynamicProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * cglib实现的动态代理
 */
public class Main2 {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);

        UserService o = (UserService) enhancer.create();
        System.out.println(o.getName(1));
        System.out.println(o.getAge(1));
        Integer.toBinaryString(10);
    }
}
