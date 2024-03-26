package com.demo.zhujie_setvalue;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class NotNullAop {
    //这里controller在idea中会报红，因为在pc-common模块中没有controller这个包
    //但是把pc-common这个模块导入其他模块之后，因为其他模块中有controller，所以是可以正常运行的，
    //无须理会这个报红
    @Pointcut("execution(public * com.demo.controller.*.*(..)))")
    public void validate(){}


    @Before("validate()")
    public void doBefore(JoinPoint joinPoint){

        //获取方法上的形参
//        Object[] params = joinPoint.getArgs();
//        if(params.length == 0){
//            return;
//        }
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
//            Object param = params[i];
            Annotation[] paramAnn = annotations[i];
            //参数为空，直接下一个参数
            if(/*param == null || */paramAnn.length == 0){
                continue;
            }
            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为Test.class
                if(annotation.annotationType().equals(NotNull.class)){
                    System.out.println("是这个注解");
                    //校验该参数，验证一次退出该注解
                    //TODO 校验参数
                    break;
                }
            }
        }
    }
}

