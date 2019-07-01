package com.demo.spring.interfac.initializingBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/31 2:04 PM
 * @Version 1.0
 **/

@Configuration
public class InitializingBeanConfiguration {

    @Bean(initMethod = "testInit")
    public InitializingBeanDemo initializingBeanDemo() {
        return new InitializingBeanDemo("longjianyong");
    }
}
