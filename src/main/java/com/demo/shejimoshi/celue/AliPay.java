package com.demo.shejimoshi.celue;

import org.springframework.stereotype.Component;

@Component
public class AliPay implements PayFacade{
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }

    @Override
    public Scene getSupportScene() {
        return Scene.ALIBABA;
    }
}
