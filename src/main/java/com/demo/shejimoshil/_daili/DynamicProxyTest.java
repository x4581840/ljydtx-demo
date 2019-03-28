package com.demo.shejimoshil._daili;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        BuyHouse buyHouseProxy = (BuyHouse)Proxy.newProxyInstance(
                                                    //BuyHouse.class.getClassLoader(),
                                                    buyHouse.getClass().getClassLoader(), //和上面注释的代码一样的效果
                                                    new Class[]{BuyHouse.class},
                                                    new DynamicProxyHandler(buyHouse));
        buyHouseProxy.buyHosue();
//        System.out.println(buyHouse);
//        System.out.println(buyHouseProxy);
    }
}
