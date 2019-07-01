package com.demo.spring.interfac.initializingBean;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/5/31 1:59 PM
 * @Version 1.0
 **/

import org.springframework.beans.factory.InitializingBean;

/**
 * InitializingBean接口为bean提供了初始化方法的方式，
 * 它只包括afterPropertiesSet方法，
 * 凡是继承该接口的类，在初始化bean的时候都会执行该方法
 */


public class InitializingBeanDemo implements InitializingBean {

    private String name;

    public InitializingBeanDemo(String name) {
        this.name = name;
    }

    /**
     * afterPropertiesSet()方法比下面的testInit()初始化方法先执行
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean====");
        System.out.println("name: " + this.name);
        //如果afterPropertiesSet报错，则不调用testInit方法
        //int i = 1/0;
    }

    public void testInit() {
        System.out.println("test init-method====");
    }

    /**
     * 从结果可以看出，在Spring初始化bean的时候，如果该bean实现了InitializingBean接口，
     * 并且同时在配置文件中指定了init-method，系统则是先调用afterPropertieSet()方法，
     * 然后再调用init-method中指定的方法。
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
