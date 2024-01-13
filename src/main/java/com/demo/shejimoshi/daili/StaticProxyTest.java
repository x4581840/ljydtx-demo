package com.demo.shejimoshi.daili;

public class StaticProxyTest {

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHosue();

        BuyHouseStaticProxy buyHouseProxy = new BuyHouseStaticProxy(buyHouse);
        buyHouseProxy.buyHosue();
    }
}
