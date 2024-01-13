package com.demo.shejimoshi.daili;

/**
 * 静态代理
 */
public class BuyHouseStaticProxy implements BuyHouse {

    private BuyHouse buyHouse;

    public BuyHouseStaticProxy(BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHosue() {
        System.out.println("买房前准备");
        buyHouse.buyHosue();
        System.out.println("买房后装修");
    }
}
