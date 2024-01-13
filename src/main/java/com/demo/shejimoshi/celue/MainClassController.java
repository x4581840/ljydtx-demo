package com.demo.shejimoshi.celue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/celue")
public class MainClassController {

    @RequestMapping("/celue")
    public void celueTest() {
//        WeiXinPay weiXinPay = (WeiXinPay) PayFactory.get(Scene.TENCENT);
        PayFacade weiXinPay = PayFactory.get(Scene.TENCENT);
        weiXinPay.pay();
        AliPay aliPay = (AliPay) PayFactory.get(Scene.ALIBABA);
        aliPay.pay();
    }

    public static void main(String[] args) {
        WeiXinPay weiXinPay = (WeiXinPay) PayFactory.get(Scene.TENCENT);
        weiXinPay.pay();
        AliPay aliPay = (AliPay) PayFactory.get(Scene.ALIBABA);
        aliPay.pay();
    }
}
