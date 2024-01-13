package com.demo.shejimoshi.daili;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib实现的动态代理
 * CGLIB创建的动态代理对象比JDK创建的动态代理对象的性能更高，但是CGLIB创建代理对象时所花费的时间却比JDK多得多。
 * 所以对于单例的对象，因为无需频繁创建对象，用CGLIB合适，反之使用JDK方式要更为合适一些。
 * 同时由于CGLib由于是采用动态创建子类的方法，对于final修饰的方法无法进行代理。
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object getInstance(final Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("买房前准备");
        //Object result = methodProxy.invokeSuper(object, args); 如果是这个，回报错
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println("买房后装修");
        return result;
    }
}
