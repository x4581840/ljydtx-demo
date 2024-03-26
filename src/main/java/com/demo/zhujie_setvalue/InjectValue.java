package com.demo.zhujie_setvalue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 注解用于参数
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时可用
public @interface InjectValue {
    String value();
}
