package com.demo.page;

/**
 * @Description
 * @Author longjianyong
 * @Date 2020/8/27 3:20 PM
 * @Version 1.0
 **/
import java.util.List;

/**
 * 分页查询接口
 *
 * @author liuzh_3nofxnp
 * @since 2015-12-18 18:51
 */
@FunctionalInterface
public interface Select<E> {

    List<E> doSelect();

}