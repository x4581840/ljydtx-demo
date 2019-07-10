/*
package com.demo.springboot_test.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

*/
/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/6 1:52 PM
 * @Version 1.0
 **//*


//springboot注册filter方式1：注解方式
//@Component
public class UserFilter4 implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserFilter4.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //启动的时候
        logger.info("UserFilter4 init..");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("UserFilter4 doFilter..");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //程序终止时调用
        logger.info("UserFilter4 destroy..");
    }
}
*/
