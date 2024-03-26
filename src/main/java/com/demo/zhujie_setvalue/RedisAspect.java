package com.demo.zhujie_setvalue;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class RedisAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Around("execution(public * com.example.service.UserService.*(..)) || " +
//            "execution(public * com.example.service.OrderService.*(..))")

    @Pointcut("execution(* com.demo.zhujie_setvalue.SomeService.one*(..))")
    public void getRedisValue1(){}

    @Pointcut("execution(* com.demo.zhujie_setvalue.SomeService.two*(..))")
    public void getRedisValue2(){}

//    @Around("@annotation(com.demo.zhujie_setvalue.FromRedis)")
//    @Around("getRedisValue1() || getRedisValue2()")
    @Around("execution(* com.demo.zhujie_setvalue.SomeService.one*(..)) || execution(* com.demo.zhujie_setvalue.SomeService.two*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("我进来了");
        //获取方法上的形参
        Object[] params = joinPoint.getArgs();
        if(params.length == 0){
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
                if(annotation.annotationType().equals(FromRedis.class)){
                    System.out.println("是这个注解");
                    FromRedis fromRedis = (FromRedis)annotation;
                    String value = stringRedisTemplate.opsForValue().get(fromRedis.key());
                    System.out.println("从Redis里面拿到的值是：" + value);
                    params[i] = value;

                    //校验该参数，验证一次退出该注解
                    //TODO 校验参数
//                    break;
                }
            }
        }
       return joinPoint.proceed(params); //执行目标方法
        // 更新参数列表并继续执行原方法
//        return pjp.proceed(args);
    }
}


