package com.jack.service.Impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author ：liyongjie
 * @ClassName ：AfterDemo
 * @date ： 2019-12-27 15:16
 * @modified By：aop实现
 */
@Component
//@Aspect
public class AfterDemo {

//    @Pointcut(execution(* com.jack..service..*.*(..))) 在service层启动是执行
//    @Pointcut("@annotation(com.jack.utils.aop.VisitCount)")
//    @Pointcut("execution(* com.jack..service..*.*(..))")
    public void access(){
        System.out.println("action");
    }

//    @Before("access()")
    public void deBefore(JoinPoint joinPoint){
        System.err.println("before");
    }

//    @AfterReturning("access()")
    public void deAfter(){
        System.err.println("after");
    }

}
