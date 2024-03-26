package com.demo.zhujie_setvalue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class Injector {

    private static final Map<String, Object> values = new HashMap<>();

    static {
        values.put("myValue", "injectedValue"); // 预设一些值
    }

    public static void inject(Object object) throws Exception {
        System.out.println("我进来了");
        for (Method method : object.getClass().getDeclaredMethods()) {
//            if (method.isAnnotationPresent(InjectValue.class)) {
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    InjectValue injectValue = parameters[i].getAnnotation(InjectValue.class);
                    if (injectValue != null) {
                        method.invoke(object, values.get(injectValue.value()));
                    }
//                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyService myService = new MyService();
        inject(myService); // 调用inject方法，自动为方法参数赋值
    }
}