package com.demo.shejimoshi.celue;

import com.google.common.collect.Maps;

import java.util.Map;

public class PayFactory {

    private static final Map<Scene, PayFacade> PAY_FACADE = Maps.newHashMap();

    public static void register(Scene scene, PayFacade payFacade) {
        PAY_FACADE.put(scene, payFacade);
    }
    public static PayFacade get(Scene scene) {
        return PAY_FACADE.get(scene);
    }
}
