/*
package com.demo.springboot_test.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/6 4:56 PM
 * @Version 1.0
 **//*


@Component
public class UserInterceptor1 implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UserInterceptor1.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("UserInterceptor1 preHandle..");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("UserInterceptor1 postHandle..");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("UserInterceptor1 afterCompletion..");
    }
}
*/
