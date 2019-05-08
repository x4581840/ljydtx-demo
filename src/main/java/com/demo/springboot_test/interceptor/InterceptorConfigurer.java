/*
package com.demo.springboot_test.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

*/
/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/6 5:08 PM
 * @Version 1.0
 **//*


//springboot1.x实现WebMvcConfigurerAdapter
//springboot2.x实现WebMvcConfigurer

//@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Bean
    public UserInterceptor1 userInterceptor1() {
        return new UserInterceptor1();
    }

    @Bean
    public UserInterceptor2 userInterceptor2() {
        return new UserInterceptor2();
    }

    //拦截器的添加顺序就是执行顺序，如上所示，第一个拦截器是userInterceptor1，第二个拦截器是userInterceptor2；
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor1()).addPathPatterns("/user/test_long");
        registry.addInterceptor(userInterceptor2()).addPathPatterns("/user/test_long1");
    }
}
*/
