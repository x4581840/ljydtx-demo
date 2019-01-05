package com.demo.guava.cache;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class Dao {
    Cache<String, List<String>> poiCache = CacheBuilder.newBuilder().build();

    @SuppressWarnings("unchecked")
    public List<String> getPOIList(final String cityId) {
        List returnList = null;
        try {
            returnList = poiCache.get(cityId, new Callable<List<String>>() {
                @Override
                public List<String> call(){
                    return getPOIListFromDB(cityId);
                }
            });
        } catch (ExecutionException e) {
            // 记日志
        }
        return Optional.fromNullable(returnList).or(Collections.EMPTY_LIST);
    }

    @SuppressWarnings("unchecked")
    private List<String> getPOIListFromDB(String cityId){
        System.out.println("getting from DB, please wait...");
        List<String> returnList = null;
        // 模仿从数据库中取数据
        try {
            Thread.sleep(1000);
            switch (cityId){
                case "0101" :
                    returnList = ImmutableList.of("望京", "望京西", "望京南", "望京北"); break;
                case "0102" :
                    returnList = ImmutableList.of("a", "b", "c", "d"); break;
            }
        } catch (Exception e) {
            // 记日志
        }
        return Optional.fromNullable(returnList).or(Collections.EMPTY_LIST);
    }
}
