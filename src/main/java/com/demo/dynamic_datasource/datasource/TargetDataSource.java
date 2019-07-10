package com.demo.dynamic_datasource.datasource;

import java.lang.annotation.*;

/**
 * @Description 在方法上使用，用于指定使用哪个数据源
 * @Author longjianyong
 * @Date 2019/5/27 10:26 AM
 * @Version 1.0
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
