package com.iprody08.inquiryservice.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLoggingAspect {

    @SneakyThrows
    @Around("execution(* com.iprody08.inquiryservice.dao.*.*(..))")
    public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final String methodName = methodSignature.getName();
        System.out.println("Begin of " + methodName);
        final Object targetMethodResult;
        targetMethodResult = proceedingJoinPoint.proceed();
        System.out.println("End of " + methodName);

        return targetMethodResult;
    }
}
