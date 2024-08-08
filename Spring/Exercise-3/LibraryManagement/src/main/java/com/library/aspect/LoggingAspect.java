package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.displayBooks(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("LoggingAspect: Before execution of method: " + joinPoint.getSignature());
    }

    @After("execution(* com.library.service.BookService.displayBooks(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("LoggingAspect: After the execution of method: " + joinPoint.getSignature());
    }
}

