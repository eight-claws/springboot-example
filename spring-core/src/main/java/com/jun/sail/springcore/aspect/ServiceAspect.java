package com.jun.sail.springcore.aspect;

import com.jun.sail.springcore.service.UserDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author wangjun
 * @Date 2020/11/23
 **/
@Aspect
@Component
public class ServiceAspect {

    @Pointcut(value = " ( execution(* com.jun..sail..*ServiceImpl.*(..)))")
    public void service() {

    }

    // JoinPoint joinPoint必须作为第一个参数，否则会报错。userDto通过注解里的args绑定实际方法参数到方法参数里
    @Before("service() && args(userDto)")
    public void doLog(JoinPoint joinPoint, UserDto userDto) {
        Signature signature = joinPoint.getSignature();

        // 通过joinPoint.getArgs()可以拿到参数，但是类型转换很麻烦
        UserDto pointArg = (UserDto) (joinPoint.getArgs()[0]);
        String name = pointArg.getName();

        System.out.println("before handler......" + userDto);
    }

    @AfterReturning(value = "service()", returning = "result")
    public void doLogAfterReturning(JoinPoint joinPoint, UserDto result) {

        System.out.println("after handler......" + result);
    }

}
