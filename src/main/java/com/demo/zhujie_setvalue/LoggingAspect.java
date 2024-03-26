package com.demo.zhujie_setvalue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // @Around("@annotation(com.example.demo.LogExecutionTime)")表示该环绕通知仅在使用@LogExecutionTime注解的方法上执行
    @Around("@annotation(logExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        System.out.println("logExecutionTime我进来了");
        long startTime = System.currentTimeMillis();

        // 打印传入的参数值
        String additionalInfo = logExecutionTime.value();
        System.out.println("Additional Info: " + additionalInfo);

        //获取方法上的形参
        Object[] args = joinPoint.getArgs();
        if(args.length == 0){
            return joinPoint.proceed();
        }
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();

        for (int i = 0; i < annotations.length; i++) {
//            Object param = params[i];
            Annotation[] paramAnn = annotations[i];
            //参数值为空，直接下一个参数
            if(/*param == null || */paramAnn.length == 0){
                continue;
            }
            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为Test.class
                if(annotation.annotationType().equals(LogExecutionTime.class)){
                    System.out.println("是这个注解");
                    LogExecutionTime logET = (LogExecutionTime)annotation;
                    String value = stringRedisTemplate.opsForValue().get(logET.value());
                    System.out.println("从Redis里面拿到的值是：" + value);
//                    args[i] = value;

                    //校验该参数，验证一次退出该注解
                    //TODO 校验参数
//                    break;
                }
            }
        }

        // 执行目标方法
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println(
                joinPoint.getSignature() + " executed in " + (endTime - startTime) + "ms");

        return result;
    }
}

