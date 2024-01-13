package com.demo.shejimoshi.celue;

import org.springframework.stereotype.Component;

@Component
public class WeiXinPay implements PayFacade{
    @Override
    public void pay() {
        System.out.println("微信支付");
    }

    @Override
    public Scene getSupportScene() {
        return Scene.TENCENT;
    }
}
