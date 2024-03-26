package com.demo.zhujie_setvalue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FromRedis {
    String key();
}
