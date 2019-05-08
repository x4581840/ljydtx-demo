/*
package com.demo.springboot_test.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

*/
/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/6 1:52 PM
 * @Version 1.0
 **//*


//springboot注册filter方式1：注解方式
@Component
@WebFilter(urlPatterns = "/user/*", filterName = "userFilter1")
@Order(1)
public class UserFilter1 implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserFilter1.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //启动的时候
        logger.info("UserFilter1 init..");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("UserFilter1 doFilter..");
        userDao.printMsg("UserFilter1 doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //程序终止时调用
        logger.info("UserFilter1 destroy..");
    }
}
*/
