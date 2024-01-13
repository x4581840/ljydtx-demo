package com.demo.shejimoshi.celue;

import org.springframework.beans.factory.InitializingBean;

public interface PayFacade extends InitializingBean {

    void pay();

    Scene getSupportScene();

    default void afterPropertiesSet() throws Exception {
        PayFactory.register(getSupportScene(), this);
    }

}
