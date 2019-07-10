package com.demo.shejimoshil._daili;

public class StaticProxyTest {

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHosue();

        BuyHouseStaticProxy buyHouseProxy = new BuyHouseStaticProxy(buyHouse);
        buyHouseProxy.buyHosue();
    }
}
