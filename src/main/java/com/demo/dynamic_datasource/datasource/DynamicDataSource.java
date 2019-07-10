package com.demo.dynamic_datasource.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description 动态数据
 * @Author longjianyong
 * @Date 2019/5/27 10:25 AM
 * @Version 1.0
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
